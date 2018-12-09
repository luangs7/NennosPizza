package br.com.luan2.nennospizza.data.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "Drinks")
data class Drinks(
    @PrimaryKey(autoGenerate = true)
   override var id: Int = 0,
    override var name: String = "",
    override var price: Double = 0.0,
    @Ignore
    override var type: CartType = CartType.DRINK,
    override var idCart: Int = 0
): ItemCart