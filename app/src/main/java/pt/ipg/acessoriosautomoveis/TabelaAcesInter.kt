package pt.ipg.acessoriosautomoveis

import android.database.sqlite.SQLiteDatabase



class TabelaAcesInter(db: SQLiteDatabase): TabelaDB(db, NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, nome TEXT NOT NULL, marca TEXT NOT NULL, descricao TEXT NOT NULL)")

    }

    companion object {
        const val NOME_TABELA = "acessoriosInterior"
    }

}