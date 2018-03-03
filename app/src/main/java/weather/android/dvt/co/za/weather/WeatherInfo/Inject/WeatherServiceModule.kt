package weather.android.dvt.co.za.weather.WeatherInfo.Inject

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder



/**
 * Created by Wolf on 03/03/2018.
 */
@Module
class WeatherServiceModule{

    @Provides
    fun retrofit(base_url: String,gsonConverterFactory: GsonConverterFactory, rxJava2CallAdapterFactory: RxJava2CallAdapterFactory) : Retrofit{
        return Retrofit.Builder()
                .baseUrl(base_url)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Provides
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    fun gsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun rxJavaCallAdapterFactory(): RxJava2CallAdapterFactory{
        return RxJava2CallAdapterFactory.create()
    }

}