package pe.gob.msi.data.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pe.gob.msi.presentation.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RestApi {
    private var retrofit: Retrofit? = null

    val retrofitInstance: Retrofit?
    get() {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                println("retrofit ======> $message")
            }

        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()


        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_API_REST)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

    /*fun getRetrofitInstance(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }*/
}