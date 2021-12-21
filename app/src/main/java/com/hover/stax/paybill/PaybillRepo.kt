package com.hover.stax.paybill

import com.hover.stax.database.AppDatabase
import kotlinx.coroutines.flow.Flow

class PaybillRepo(db: AppDatabase) {

    private val paybillDao: PaybillDao = db.paybillDao()

    val allBills: Flow<List<Paybill>> = paybillDao.allBills

    fun getSavedPaybills(accountId: Int): Flow<List<Paybill>> = paybillDao.getSavedPaybills(accountId)

    fun getPaybills(accountId: Int): Flow<List<Paybill>> = paybillDao.getPaybillsByAccount(accountId)

    fun save(paybill: Paybill) = paybillDao.insert(paybill)

    fun update(paybill: Paybill) = paybillDao.update(paybill)
}