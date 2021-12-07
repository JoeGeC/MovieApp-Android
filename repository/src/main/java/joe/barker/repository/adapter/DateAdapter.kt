package joe.barker.repository.adapter

import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal fun String?.convertDate(): LocalDate {
    return if (this == null) LocalDate.of(0, 1, 1)
    else LocalDate.parse(this, DateTimeFormatter.ISO_DATE)
}
