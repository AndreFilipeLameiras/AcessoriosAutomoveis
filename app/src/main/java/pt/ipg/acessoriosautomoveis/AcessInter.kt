package pt.ipg.acessoriosautomoveis

data class AcessInter(
    var nome: String,
    var marca: String,
    var descricao: String,
    var id: Long = -1
) {
}