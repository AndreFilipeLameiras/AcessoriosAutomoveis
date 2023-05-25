package pt.ipg.acessoriosautomoveis

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns



class TabelaCarro (db: SQLiteDatabase): TabelaDB(db, NOME_TABELA){
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, marca TEXT NOT NULL, cor TEXT, categoria TEXT NOT NULL, id_acesInter INTEGER NOT NULL, id_acesExter INTEGER NOT NULL, FOREIGN KEY (id_acesInter) REFERENCES ${TabelaAcesInter.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT, FOREIGN KEY (id_acesExter) REFERENCES ${TabelaAcesExter.NOME_TABELA}(${BaseColumns._ID}) ON DELETE RESTRICT )")
    }

    companion object{
        const val NOME_TABELA = "carro"
    }

}