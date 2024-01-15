package serializables

import kotlinx.serialization.Serializable


/** Data class used to store settings in settings.json*/
@Serializable
data class Settings (val Address :String, val Port :String
)
