package pt.ipg.acessoriosautomoveis

import android.database.sqlite.SQLiteDatabase



class TabelaAcesInter(db: SQLiteDatabase): TabelaDB(db, NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, $CAMPO_NOME TEXT NOT NULL, $CAMPO_MARCA TEXT NOT NULL, $CAMPO_DESCRICAO TEXT NOT NULL)")

    }

    companion object {
        const val NOME_TABELA = "acessoriosInterior"
        const val CAMPO_NOME = "nome"
        const val CAMPO_MARCA = "marca"
        const val CAMPO_DESCRICAO = "descricao"
    }

}