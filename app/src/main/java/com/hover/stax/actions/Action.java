package com.hover.stax.actions;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// This Entity reads the SDK's database, so the fields below have to match the SDK's SQL definition EXACTLY
// since the SDK does not currently use Room
@Entity(tableName = "hsdk_actions")
public class Action {

	@PrimaryKey
	@ColumnInfo(name = "_id")
	public int id;

	@NonNull
	@ColumnInfo(name = "server_id")
	public String public_id;

	@NonNull
	@ColumnInfo(name = "channel_id")
	public int channel_id;

	@NonNull
	@ColumnInfo(name = "transaction_type")
	public String transaction_type;

	@NonNull
	@ColumnInfo(name = "from_institution_id")
	public int from_institution_id;

	@ColumnInfo(name = "to_institution_id")
	public Integer to_institution_id;

	@NonNull
	@ColumnInfo(name = "network_name")
	public String network_name;

	@NonNull
	@ColumnInfo(name = "hni_list")
	public String hni_list;

	@ColumnInfo(name = "country_alpha2")
	public String country_alpha2;

	@ColumnInfo(name = "custom_steps")
	public String custom_steps;

	@ColumnInfo(name = "transport_type", defaultValue = "ussd")
	public String transport_type;

	@ColumnInfo(name = "created_timestamp", defaultValue = "CURRENT_TIMESTAMP")
	public Long created_timestamp;

	@ColumnInfo(name = "updated_timestamp", defaultValue = "CURRENT_TIMESTAMP")
	public Long updated_timestamp;

	@ColumnInfo(name = "responses_are_sms")
	public Integer responses_are_sms;

	@ColumnInfo(name = "root_code")
	public String root_code;

}