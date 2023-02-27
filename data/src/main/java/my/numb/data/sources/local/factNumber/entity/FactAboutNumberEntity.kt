package my.numb.data.sources.local.factNumber.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "interesting_facts")
data class FactAboutNumberEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "number") val number: Long,
    @ColumnInfo(name = "fact") val fact: String
)
