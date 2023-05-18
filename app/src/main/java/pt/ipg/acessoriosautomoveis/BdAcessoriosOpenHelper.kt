package pt.ipg.acessoriosautomoveis

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val NOME_BASE_DADOS = "acessorios.db"
private const val VERSAO_BASE_DADOS = 1



class BdAcessoriosOpenHelper(
    context: Context?
) : SQLiteOpenHelper(context, NOME_BASE_DADOS, null, VERSAO_BASE_DADOS) {




    override fun onCreate(db: SQLiteDatabase?) {
        requireNotNull(db)
        TabelaAcesInter(db).cria()
        TabelaAcesExter(db).cria()
        //TabelaCarro(db).cria()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}