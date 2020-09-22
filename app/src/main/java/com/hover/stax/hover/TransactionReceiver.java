package com.hover.stax.hover;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.hover.sdk.transactions.TransactionContract;
import com.hover.stax.actions.Action;
import com.hover.stax.channels.Channel;
import com.hover.stax.database.DatabaseRepo;
import com.hover.stax.transactions.StaxTransaction;
import com.hover.stax.utils.DateUtils;

import java.util.HashMap;

public class TransactionReceiver extends BroadcastReceiver {
	final private static String TAG = "TransactionReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		DatabaseRepo repo = new DatabaseRepo((Application) context.getApplicationContext());
		updateBalance(repo, intent);
		updateTransaction(repo, intent);
	}

	private void updateBalance(DatabaseRepo repo, Intent intent) {
		if (intent.hasExtra("parsed_variables")) {
			HashMap<String, String> parsed_variables = (HashMap<String, String>) intent.getSerializableExtra("parsed_variables");
			if (parsed_variables.containsKey("balance")) {
				new Thread(() -> {
					Action action = repo.getAction(intent.getStringExtra("action_id"));
					Channel channel = repo.getChannel(action.channel_id);
					channel.updateBalance(parsed_variables);
					repo.update(channel);
				}).start();
			}
		}
	}

	private void updateTransaction(DatabaseRepo repo, Intent intent) {
		new Thread(() -> {
			StaxTransaction t = repo.getTransaction(intent.getStringExtra(TransactionContract.COLUMN_UUID));
			t.update(intent);
			repo.update(t);
		}).start();
	}
}
