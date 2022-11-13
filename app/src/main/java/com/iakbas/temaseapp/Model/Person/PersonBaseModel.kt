import java.io.Serializable

data class PersonBaseModel (

	val id : Int,
	val cast : List<Cast>,
	val crew : List<Crew>
) : Serializable