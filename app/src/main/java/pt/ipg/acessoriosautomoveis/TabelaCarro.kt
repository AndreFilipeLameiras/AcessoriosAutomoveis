package pt.ipg.acessoriosautomoveis

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns



class TabelaCarro (db: SQLiteDatabase): TabelaDB(db, NOME_TABELA){
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, $CAMPO_MARCA TEXT NOT NULL, $CAMPO_COR TEXT NOT NULL, $CAMPO_FK_ACESSINTER INTEGER NOT NULL, $CAMPO_FK_ACESSEXTER INTEGER NOT NULL, FOREIGN KEY ($CAMPO_FK_ACESSINTER) REFERENCES ${TabelaAcesInter.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT, FOREIGN KEY ($CAMPO_FK_ACESSEXTER) REFERENCES ${TabelaAcesExter.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT )")
    }

    companion object{
        const val NOME_TABELA = "carro"
        const val CAMPO_MARCA = "marca"
        const val CAMPO_COR = "cor"
        const val CAMPO_FK_ACESSINTER = "id_acinter"
        const val CAMPO_FK_ACESSEXTER = "id_acexter"

        val CAMPOS = arrayOf(BaseColumns._ID, CAMPO_MARCA, CAMPO_COR, CAMPO_FK_ACESSINTER, CAMPO_FK_ACESSEXTER)
    }

}