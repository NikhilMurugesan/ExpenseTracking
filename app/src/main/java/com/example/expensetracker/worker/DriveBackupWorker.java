package com.example.expensetracker.worker;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.expensetracker.data.AppDatabase;
import com.example.expensetracker.data.entity.Transaction;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

public class DriveBackupWorker extends Worker {

    public DriveBackupWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.i("DriveBackup", "Starting Google Drive backup...");

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (account == null) {
            Log.e("DriveBackup", "User is not signed in to Google.");
            return Result.failure();
        }

        GoogleAccountCredential credential = GoogleAccountCredential.usingOAuth2(
                getApplicationContext(), Collections.singleton(DriveScopes.DRIVE_FILE));
        credential.setSelectedAccount(account.getAccount());

        Drive driveService = new Drive.Builder(
                new NetHttpTransport(),
                GsonFactory.getDefaultInstance(),
                credential)
                .setApplicationName("Expense Tracker App")
                .build();

        try {
            AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
            List<Transaction> transactions = db.transactionDao().getAllSynchronous();

            String jsonPayload = new Gson().toJson(transactions);

            File fileMetadata = new File();
            fileMetadata.setName("ExpenseTracker_Backup_" + System.currentTimeMillis() + ".json");
            fileMetadata.setMimeType("application/json");
            
            // Note: Uploading to application data folder so user doesn't accidentally delete or pollute root Drive.
            fileMetadata.setParents(Collections.singletonList("appDataFolder"));

            ByteArrayInputStream contentStream = new ByteArrayInputStream(jsonPayload.getBytes(StandardCharsets.UTF_8));
            InputStreamContent mediaContent = new InputStreamContent("application/json", contentStream);

            File file = driveService.files().create(fileMetadata, mediaContent)
                    .setFields("id")
                    .execute();

            Log.i("DriveBackup", "Backup successful. File ID: " + file.getId());
            return Result.success();

        } catch (Exception e) {
            Log.e("DriveBackup", "Backup failed: ", e);
            return Result.retry();
        }
    }
}
