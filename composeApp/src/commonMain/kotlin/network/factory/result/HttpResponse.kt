package network.factory.result

interface HttpResponse {
    val statusCode: Int
    val statusMessage: String?
    val url: String?
    val errorBody: String?
}
