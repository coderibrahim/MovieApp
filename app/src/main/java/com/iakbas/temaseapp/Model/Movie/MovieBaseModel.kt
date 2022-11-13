import java.io.Serializable

data class MovieBaseModel (

	val page : Int,
	val results : List<Results>,
	val total_pages : Int,
	val total_results : Int
) : Serializable