package com.hover.stax.data.local.parser

import com.hover.sdk.database.HoverRoomDatabase
import com.hover.sdk.parsers.ParserDao

class ParserRepo(sdkDb: HoverRoomDatabase) {
	private val parserDao: ParserDao = sdkDb.parserDao()

	fun hasSMSParser(actionId: String) : Boolean = parserDao.getSMSActionParsers(actionId).isNotEmpty()
}