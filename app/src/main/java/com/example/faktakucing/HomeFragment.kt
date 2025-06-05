package com.example.faktakucing

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Date

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var catImageView: ImageView
    private lateinit var factTextView: TextView
    private lateinit var buttonRefresh: Button
    private lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        catImageView = view.findViewById(R.id.catImageView)
        factTextView = view.findViewById(R.id.factTextView)
        buttonRefresh = view.findViewById(R.id.buttonRefresh)
        progressBar = view.findViewById(R.id.progressBar)

        ambilFaktaDanGambar()

        buttonRefresh.setOnClickListener {
            ambilFaktaDanGambar()
        }
    }

    private fun ambilFaktaDanGambar() {
        // Tampilkan loading spinner, sembunyikan gambar & tombol
        progressBar.visibility = View.VISIBLE
        catImageView.visibility = View.INVISIBLE
        buttonRefresh.visibility = View.GONE

        val timestamp = Date().time
        val dynamicImageUrl = "https://cataas.com/cat?_=$timestamp"

        // Load gambar dengan Glide + Callback
        Glide.with(requireContext())
            .load(dynamicImageUrl)
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(object : CustomTarget<android.graphics.drawable.Drawable>() {
                override fun onResourceReady(
                    resource: android.graphics.drawable.Drawable,
                    transition: Transition<in android.graphics.drawable.Drawable>?
                ) {
                    catImageView.setImageDrawable(resource)
                    catImageView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    buttonRefresh.visibility = View.VISIBLE
                }

                override fun onLoadCleared(placeholder: android.graphics.drawable.Drawable?) {
                    // Kosongkan jika tak perlu
                }

                override fun onLoadFailed(errorDrawable: android.graphics.drawable.Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    progressBar.visibility = View.GONE
                    catImageView.visibility = View.VISIBLE
                    buttonRefresh.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), "Gagal memuat gambar", Toast.LENGTH_SHORT).show()
                }
            })

        // Panggil API fakta kucing
        ApiClient.instance.getCatFact().enqueue(object : Callback<CatFactResponse> {
            override fun onResponse(call: Call<CatFactResponse>, response: Response<CatFactResponse>) {
                if (response.isSuccessful) {
                    factTextView.text = response.body()?.fact ?: "Fakta tidak tersedia"
                } else {
                    Toast.makeText(requireContext(), "Gagal memuat fakta", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CatFactResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Kesalahan: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}