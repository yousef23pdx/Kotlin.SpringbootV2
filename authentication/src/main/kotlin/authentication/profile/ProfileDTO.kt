package authentication.profile

data class ProfileRequest(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String
)