package com.achsanit.jokesapp.data.response

import com.google.gson.annotations.SerializedName

data class JokesResponse(

	@field:SerializedName("result")
	val result: List<ResultItem?>? = null,

	@field:SerializedName("total")
	val total: Int? = null
)

data class ResultItem(

	@field:SerializedName("icon_url")
	val iconUrl: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("categories")
	val categories: List<Any?>? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("value")
	val value: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
