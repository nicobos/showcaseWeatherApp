package weather.android.dvt.co.za.weather.WeatherInfo.di.Modules

import dagger.Module
import dagger.Provides
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Wolf on 04/03/2018.
 */
@Module
class DateModule{

    @Provides
    fun getDate(date: Date, simpleDateFormat: SimpleDateFormat): String{
        return simpleDateFormat.format(date)
    }

    @Provides
    fun simpleDateFormat(): SimpleDateFormat{
        return SimpleDateFormat("EEE dd MMM  yyyy", Locale.ENGLISH)
    }

    @Provides
    fun date(calendar: Calendar):Date{
        return calendar.time
    }

    @Provides
    fun calender(): Calendar{
        return Calendar.getInstance()
    }

}