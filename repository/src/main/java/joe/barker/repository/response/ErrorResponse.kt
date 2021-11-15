package joe.barker.repository.response

data class ErrorResponse(
    val status_code: Int,
    val status_message: String
) {
}