package com.example.faktakucing // Pastikan package sama

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

// Konstruktor menerima daftar URL gambar dan nama ras
class BreedImageAdapter(
    private val images: List<String>,
    private val breedName: String // Anda meneruskan breedName, jadi kita terima di sini
) : RecyclerView.Adapter<BreedImageAdapter.BreedImageViewHolder>() {

    // 1. ViewHolder untuk setiap item dalam RecyclerView
    class BreedImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.itemImageView)
        val nameTextView: TextView = itemView.findViewById(R.id.itemNameTextView)

        // Ganti dengan ID ImageView di layout item Anda
        // Anda bisa menambahkan TextView untuk breedName di sini jika mau menampilkannya per gambar
        // val nameTextView: TextView = itemView.findViewById(R.id.itemNameTextView)
    }

    // 2. Membuat ViewHolder baru (dipanggil oleh layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedImageViewHolder {
        // Inflate layout untuk satu item gambar
        // Pastikan Anda memiliki file layout bernama 'item_breed_image.xml' (atau nama lain yang sesuai)
        // di direktori res/layout
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_breed_image, parent, false) // Ganti dengan nama file layout item Anda
        return BreedImageViewHolder(view)
    }

    // 3. Mengikat data ke ViewHolder (dipanggil oleh layout manager)
    override fun onBindViewHolder(holder: BreedImageViewHolder, position: Int) {
        val imageUrl = images[position]
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.loading) // Ganti dengan placeholder Anda
            .diskCacheStrategy(DiskCacheStrategy.ALL) // caching gambar
            .error(R.drawable.loading) // Ganti dengan gambar error Anda
            .into(holder.imageView)

        // Jika Anda ingin menampilkan breedName pada setiap item (misalnya jika ada TextView di item layout):
        holder.nameTextView.text = breedName
    }

    // 4. Mengembalikan jumlah total item dalam dataset
    override fun getItemCount(): Int {
        return images.size
    }
}