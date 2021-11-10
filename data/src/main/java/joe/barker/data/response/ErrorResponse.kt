package joe.barker.data.response

data class ErrorResponse(
    val status_code: Int,
    val status_message: String
) {
}