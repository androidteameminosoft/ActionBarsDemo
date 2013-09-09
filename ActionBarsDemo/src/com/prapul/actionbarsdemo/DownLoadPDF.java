package com.prapul.actionbarsdemo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DownLoadPDF extends Activity {
	TextView tv_loading;
	String dest_file_path = "test.pdf";
	int downloadedSize = 0, totalsize;
	// String download =
	// "http://ilabs.uw.edu/sites/default/files/sample_0.pdf";

	// String download_file_url =
	// "http://www.childrensbooksforever.com/Childrenpics/ROYAL%20RAVEN.pdf";
	// oyer ride
	String download_file_url = "http://datastore04.rediff.com/h450-w670/thumb/69586A645B6D2A2E3131/pvwbsmbfdegzorwo.D.0.Holi-Wallpapers-Images.jpg";
	float per = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layuot = new LinearLayout(DownLoadPDF.this);

		tv_loading = new TextView(this);
		tv_loading.setGravity(Gravity.CENTER);
		// layuot.addView(tv_loading);
		// layuot.setLayoutParams(LinearLayout.)
		tv_loading.setTypeface(null, Typeface.BOLD);
		setContentView(tv_loading);
		downloadAndOpenPDF();
	}

	void downloadAndOpenPDF() {
		new Thread(new Runnable() {
			public void run() {
				Uri path = Uri.fromFile(downloadFile(download_file_url));
				try {
					// Intent intent = new Intent(Intent.ACTION_VIEW);
					// intent.setDataAndType(path, "application/pdf");
					// intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					// startActivity(intent);
					// finish();

					// Uri uri = Uri.parse("/mnt/sdcard/App.pdf");
					// Intent in = new Intent(DownLoadPDF.this,
					// MuPDFActivity.class);
					// in.setAction(Intent.ACTION_VIEW);
					// in.setData(path);
					// DownLoadPDF.this.startActivity(in);
					// finish();
					
					

				} catch (ActivityNotFoundException e) {
					tv_loading
							.setError("PDF Reader application is not installed in your device");
				}
			}
		}).start();

	}

	File downloadFile(String dwnload_file_path) {
		File file = null;
		try {

			URL url = new URL(dwnload_file_path);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();

			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);

			// connect
			urlConnection.connect();

			file = new File(DownLoadPDF.this.getDir("filesdir",
					Context.MODE_PRIVATE) + "/yourfile.jpg");

			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();

			// set the path where we want to save the file
			// File SDCardRoot = Environment.getExternalStorageDirectory();
			// // create a new file, to save the downloaded file
			// file = new File(SDCardRoot, dest_file_path);
			//
			FileOutputStream fileOutput = new FileOutputStream(file);

			// Stream used for reading the data from the internet
			InputStream inputStream = urlConnection.getInputStream();

			// this is the total size of the file which we are
			// downloading
			totalsize = urlConnection.getContentLength();
			setText("Starting PDF download...");

			// create a buffer...
			byte[] buffer = new byte[1024 * 1024];
			int bufferLength = 0;

			while ((bufferLength = inputStream.read(buffer)) > 0) {
				fileOutput.write(buffer, 0, bufferLength);
				downloadedSize += bufferLength;
				per = ((float) downloadedSize / totalsize) * 100;
				setText("Total PDF File size  : " + (totalsize / 1024)
						+ " KB\n\nDownloading PDF " + (int) per + "% complete");

				// setText("configuring your book pleease wait a moment");
			}
			// close the output stream when complete //
			fileOutput.close();
			// setText("Download Complete. Open PDF Application installed in the device.");
			setText("configuaration is completed now your book is ready to read");
		} catch (final MalformedURLException e) {
			setTextError("Some error occured. Press back and try again.",
					Color.RED);
		} catch (final IOException e) {
			setTextError("Some error occured. Press back and try again.",
					Color.RED);
		} catch (final Exception e) {
			setTextError(
					"Failed to download image. Please check your internet connection.",
					Color.RED);
		}
		return file;
	}

	void setTextError(final String message, final int color) {
		runOnUiThread(new Runnable() {
			public void run() {
				tv_loading.setTextColor(color);
				tv_loading.setText(message);
			}
		});

	}

	void setText(final String txt) {
		runOnUiThread(new Runnable() {
			public void run() {
				tv_loading.setText(txt);
			}
		});

	}
}
