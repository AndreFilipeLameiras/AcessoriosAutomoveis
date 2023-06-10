package pt.ipg.acessoriosautomoveis

import android.database.sqlite.SQLiteDatabase



class TabelaAcesExter (db: SQLiteDatabase): TabelaDB(db, NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA ($CHAVE_TABELA, $CAMPO_NOME TEXT NOT NULL, $CAMPO_MARCA TEXT NOT NULL, $CAMPO_COR TEXT )")

    }

    companion object {
        const val NOME_TABELA = "acessoriosExterior"
        const val CAMPO_NOME = "nome"
        const val CAMPO_MARCA = "marca"
        const val CAMPO_COR = "cor"
    }

}