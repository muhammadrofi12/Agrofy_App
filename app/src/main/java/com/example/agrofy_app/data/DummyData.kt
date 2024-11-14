package com.example.agrofy_app.data

import com.example.agrofy_app.models.Videos
import com.example.agrofy_app.R
import com.example.agrofy_app.models.Articles
import com.example.agrofy_app.models.Comments
import com.example.agrofy_app.models.Weather
import com.example.agrofy_app.models.Limbah


object DummyData {
    val limbahItem = listOf(
        Limbah(
            id = 1,
            nama = "Jerami",
            photo = R.drawable.jerami,
            berat = 14,
            deskripsi = "Jerami dengan kualitas yang bagus dan sangat kering",
            tggl_masuk = 20241025,
            tggl_keluar = 20241101,
            status = "", // masuk, tenggat, sukses, gagal
            kategori = "Limbah"
        ),
        Limbah(
            id = 2,
            nama = "Atap Jerami",
            photo = R.drawable.atap_jerami,
            berat = 14,
            deskripsi = "Atap Jerami dengan kualitas yang tinggi.",
            tggl_masuk = 20241025,
            tggl_keluar = 20241101,
            status = "",
            kategori = "Progres"
        ),
        Limbah(
            id = 3,
            nama = "Jeramiiii",
            photo = R.drawable.jerami,
            berat = 14,
            deskripsi = "Jerami dengan kualitas yang bagus dan sangat kering",
            tggl_masuk = 20241025,
            tggl_keluar = 20241101,
            status = "",
            kategori = "limbah"
        ),
        Limbah(
            id = 4,
            nama = "Atap Jerami",
            photo = R.drawable.atap_jerami,
            berat = 14,
            deskripsi = "Atap Jerami dengan kualitas yang tinggi.",
            tggl_masuk = 20241025,
            tggl_keluar = 20241101,
            status = "",
            kategori = "'Progres"
        ),
        Limbah(
            id = 5,
            nama = "Jeramiiiiiii",
            photo = R.drawable.jerami,
            berat = 14,
            deskripsi = "Jerami dengan kualitas yang bagus dan sangat kering",
            tggl_masuk = 20241025,
            tggl_keluar = 20241101,
            status = "",
            kategori = "Limbah"
        ),
        Limbah(
            id = 6,
            nama = "Jerami",
            photo = R.drawable.jerami,
            berat = 14,
            deskripsi = "Jerami dengan kualitas yang bagus dan sangat kering",
            tggl_masuk = 20241025,
            tggl_keluar = 20241101,
            status = "",
            kategori = "Limbah"
        ),
        Limbah(
            id = 7,
            nama = "Jerami",
            photo = R.drawable.jerami,
            berat = 14,
            deskripsi = "Jerami dengan kualitas yang bagus dan sangat kering",
            tggl_masuk = 20241025,
            tggl_keluar = 20241101,
            status = "",
            kategori = "Limbah"
        ),
        Limbah(
            id = 8,
            nama = "Jerami",
            photo = R.drawable.jerami,
            berat = 14,
            deskripsi = "Jerami dengan kualitas yang bagus dan sangat kering",
            tggl_masuk = 20241025,
            tggl_keluar = 20241101,
            status = "",
            kategori = "Limbah"
        ),
    )
    val videoPembelajaran = listOf(
        Videos(
            id = 1,
            judul = "Pembuatan Briket Dari Bongol Jagung",
            sub_judul = "Arang briket dari bonggol jagung adalah bahan bakar alternatif yang ramah lingkungan, terbuat dari limbah pertanian bonggol jagung yang kering. Proses pembuatannya meliputi pirolisis (pembakaran tanpa oksigen), penghalusan arang, pencampuran dengan bahan pengikat, pencetakan, dan pengeringan.",
            deskripsi = """
                Cara Membuat Arang Briket dari Bonggol Jagung 🌽
                1. Persiapan: Bonggol jagung dikeringkan hingga benar-benar kering.
                2. Pirolisis: Bonggol kering dibakar dalam drum tertutup tanpa oksigen agar menjadi arang, bukan abu.
                3. Penghalusan: Arang yang dihasilkan dihancurkan menjadi serbuk halus. 
                4. Pencampuran: Serbuk arang dicampur dengan bahan pengikat seperti tepung kanji, ditambahkan air secukupnya hingga menjadi adonan.
                5. Pencetakan: Adonan dicetak sesuai bentuk yang diinginkan dengan tekanan yang cukup.
                6. Pengeringan: Briket yang sudah dicetak dijemur atau dikeringkan hingga benar-benar kering.
                
                Keunggulan 🌟
                1. Ramah Lingkungan: Memanfaatkan limbah pertanian.
                2. Ekonomis: Lebih murah dan mudah didapat.
                3. Stabil: Panas yang konsisten dan emisi rendah.
                
                Proses ini mengubah limbah bonggol jagung menjadi sumber energi alternatif yang efisien dan berkelanjutan!
                """.trimIndent(),
            tanggal = 20241025,
            kategori = "Jagung",
            duration = "03:35",
            photo = R.drawable.video_thumb,
            file_video = "https://drive.google.com/uc?export=download&id=1N6uujM7zrjK2e1ZBja6OEhflFKZlmHg8",
            author = "Willy Candra",
        ),
        Videos(
            id = 2,
            judul = "Membuat Kerajinan Bunga Dari Kulit Jagung",
            sub_judul = "Kulit jagung kering diubah menjadi bunga hias cantik melalui pemotongan, pewarnaan, dan penyusunan kelopak. Dengan bahan sederhana, tercipta dekorasi ramah lingkungan yang cocok untuk mempercantik rumah atau menjadi hadiah kreatif.",
            deskripsi = """
                Cara Membuat Arang Briket dari Bonggol Jagung 🌽
                1. Persiapan: Bonggol jagung dikeringkan hingga benar-benar kering.
                2. Pirolisis: Bonggol kering dibakar dalam drum tertutup tanpa oksigen agar menjadi arang, bukan abu.
                3. Penghalusan: Arang yang dihasilkan dihancurkan menjadi serbuk halus. 
                4. Pencampuran: Serbuk arang dicampur dengan bahan pengikat seperti tepung kanji, ditambahkan air secukupnya hingga menjadi adonan.
                5. Pencetakan: Adonan dicetak sesuai bentuk yang diinginkan dengan tekanan yang cukup.
                6. Pengeringan: Briket yang sudah dicetak dijemur atau dikeringkan hingga benar-benar kering.
                
                Keunggulan 🌟
                1. Ramah Lingkungan: Memanfaatkan limbah pertanian.
                2. Ekonomis: Lebih murah dan mudah didapat.
                3. Stabil: Panas yang konsisten dan emisi rendah.
                
                Proses ini mengubah limbah bonggol jagung menjadi sumber energi alternatif yang efisien dan berkelanjutan!
                """.trimIndent(),
            tanggal = 20241025,
            kategori = "Jagung",
            duration = "03:35",
            photo = R.drawable.thumb2,
            file_video = "https://drive.google.com/uc?export=download&id=1N6uujM7zrjK2e1ZBja6OEhflFKZlmHg8",
            author = "Willy Candra",
        ),
        Videos(
            id = 3,
            judul = "Membuat Pupuk Bokashi (Bahan Organik Kaya Sumber Hayati) Dari Jerami",
            sub_judul = "Bokashi adalah pupuk kompos berbasis EM4 yang mempercepat fermentasi menjadi 7 hari. Pupuk ini memperbaiki kualitas tanah, meningkatkan hasil pertanian, mempermudah penyerapan air, dan mengurangi ke-lengket-an tanah untuk memudahkan pembajakan.",
            deskripsi = """
                Cara Membuat Arang Briket dari Bonggol Jagung 🌽
                1. Persiapan: Bonggol jagung dikeringkan hingga benar-benar kering.
                2. Pirolisis: Bonggol kering dibakar dalam drum tertutup tanpa oksigen agar menjadi arang, bukan abu.
                3. Penghalusan: Arang yang dihasilkan dihancurkan menjadi serbuk halus. 
                4. Pencampuran: Serbuk arang dicampur dengan bahan pengikat seperti tepung kanji, ditambahkan air secukupnya hingga menjadi adonan.
                5. Pencetakan: Adonan dicetak sesuai bentuk yang diinginkan dengan tekanan yang cukup.
                6. Pengeringan: Briket yang sudah dicetak dijemur atau dikeringkan hingga benar-benar kering.
                
                Keunggulan 🌟
                1. Ramah Lingkungan: Memanfaatkan limbah pertanian.
                2. Ekonomis: Lebih murah dan mudah didapat.
                3. Stabil: Panas yang konsisten dan emisi rendah.
                
                Proses ini mengubah limbah bonggol jagung menjadi sumber energi alternatif yang efisien dan berkelanjutan!
                """.trimIndent(),
            tanggal = 20241025,
            kategori = "Padi",
            duration = "03:35",
            photo = R.drawable.thumb3,
            file_video = "https://drive.google.com/uc?export=download&id=1N6uujM7zrjK2e1ZBja6OEhflFKZlmHg8",
            author = "Willy Candra",
        ),
        Videos(
            id = 4,
            judul = "Pembuatan Briket Dari Bongol Jagung",
            sub_judul = "Arang briket dari bonggol jagung adalah bahan bakar alternatif yang ramah lingkungan, terbuat dari limbah pertanian bonggol jagung yang kering. Proses pembuatannya meliputi pirolisis (pembakaran tanpa oksigen), penghalusan arang, pencampuran dengan bahan pengikat, pencetakan, dan pengeringan.",
            deskripsi = """
                Cara Membuat Arang Briket dari Bonggol Jagung 🌽
                1. Persiapan: Bonggol jagung dikeringkan hingga benar-benar kering.
                2. Pirolisis: Bonggol kering dibakar dalam drum tertutup tanpa oksigen agar menjadi arang, bukan abu.
                3. Penghalusan: Arang yang dihasilkan dihancurkan menjadi serbuk halus. 
                4. Pencampuran: Serbuk arang dicampur dengan bahan pengikat seperti tepung kanji, ditambahkan air secukupnya hingga menjadi adonan.
                5. Pencetakan: Adonan dicetak sesuai bentuk yang diinginkan dengan tekanan yang cukup.
                6. Pengeringan: Briket yang sudah dicetak dijemur atau dikeringkan hingga benar-benar kering.
                
                Keunggulan 🌟
                1. Ramah Lingkungan: Memanfaatkan limbah pertanian.
                2. Ekonomis: Lebih murah dan mudah didapat.
                3. Stabil: Panas yang konsisten dan emisi rendah.
                
                Proses ini mengubah limbah bonggol jagung menjadi sumber energi alternatif yang efisien dan berkelanjutan!
                """.trimIndent(),
            tanggal = 20241025,
            kategori = "Jagung",
            duration = "03:35",
            photo = R.drawable.video_thumb,
            file_video = "https://drive.google.com/uc?export=download&id=1N6uujM7zrjK2e1ZBja6OEhflFKZlmHg8",
            author = "Willy Candra",
        ),
        Videos(
            id = 5,
            judul = "Pembuatan Briket Dari Bongol Jagung",
            sub_judul = "Arang briket dari bonggol jagung adalah bahan bakar alternatif yang ramah lingkungan, terbuat dari limbah pertanian bonggol jagung yang kering. Proses pembuatannya meliputi pirolisis (pembakaran tanpa oksigen), penghalusan arang, pencampuran dengan bahan pengikat, pencetakan, dan pengeringan.",
            deskripsi = """
                Cara Membuat Arang Briket dari Bonggol Jagung 🌽
                1. Persiapan: Bonggol jagung dikeringkan hingga benar-benar kering.
                2. Pirolisis: Bonggol kering dibakar dalam drum tertutup tanpa oksigen agar menjadi arang, bukan abu.
                3. Penghalusan: Arang yang dihasilkan dihancurkan menjadi serbuk halus. 
                4. Pencampuran: Serbuk arang dicampur dengan bahan pengikat seperti tepung kanji, ditambahkan air secukupnya hingga menjadi adonan.
                5. Pencetakan: Adonan dicetak sesuai bentuk yang diinginkan dengan tekanan yang cukup.
                6. Pengeringan: Briket yang sudah dicetak dijemur atau dikeringkan hingga benar-benar kering.
                
                Keunggulan 🌟
                1. Ramah Lingkungan: Memanfaatkan limbah pertanian.
                2. Ekonomis: Lebih murah dan mudah didapat.
                3. Stabil: Panas yang konsisten dan emisi rendah.
                
                Proses ini mengubah limbah bonggol jagung menjadi sumber energi alternatif yang efisien dan berkelanjutan!
                """.trimIndent(),
            tanggal = 20241025,
            kategori = "Jagung",
            duration = "03:35",
            photo = R.drawable.video_thumb,
            file_video = "https://drive.google.com/uc?export=download&id=1N6uujM7zrjK2e1ZBja6OEhflFKZlmHg8",
            author = "Willy Candra",
        ),
        Videos(
            id = 6,
            judul = "Pembuatan Briket Dari Bongol Jagung",
            sub_judul = "Arang briket dari bonggol jagung adalah bahan bakar alternatif yang ramah lingkungan, terbuat dari limbah pertanian bonggol jagung yang kering. Proses pembuatannya meliputi pirolisis (pembakaran tanpa oksigen), penghalusan arang, pencampuran dengan bahan pengikat, pencetakan, dan pengeringan.",
            deskripsi = """
                Cara Membuat Arang Briket dari Bonggol Jagung 🌽
                1. Persiapan: Bonggol jagung dikeringkan hingga benar-benar kering.
                2. Pirolisis: Bonggol kering dibakar dalam drum tertutup tanpa oksigen agar menjadi arang, bukan abu.
                3. Penghalusan: Arang yang dihasilkan dihancurkan menjadi serbuk halus. 
                4. Pencampuran: Serbuk arang dicampur dengan bahan pengikat seperti tepung kanji, ditambahkan air secukupnya hingga menjadi adonan.
                5. Pencetakan: Adonan dicetak sesuai bentuk yang diinginkan dengan tekanan yang cukup.
                6. Pengeringan: Briket yang sudah dicetak dijemur atau dikeringkan hingga benar-benar kering.
                
                Keunggulan 🌟
                1. Ramah Lingkungan: Memanfaatkan limbah pertanian.
                2. Ekonomis: Lebih murah dan mudah didapat.
                3. Stabil: Panas yang konsisten dan emisi rendah.
                
                Proses ini mengubah limbah bonggol jagung menjadi sumber energi alternatif yang efisien dan berkelanjutan!
                """.trimIndent(),
            tanggal = 20241025,
            kategori = "Jagung",
            duration = "03:35",
            photo = R.drawable.video_thumb,
            file_video = "https://drive.google.com/uc?export=download&id=1N6uujM7zrjK2e1ZBja6OEhflFKZlmHg8",
            author = "Willy Candra",
        ),
        Videos(
            id = 7,
            judul = "Pembuatan Briket Dari Pisang",
            sub_judul = "Arang briket dari bonggol jagung adalah bahan bakar alternatif yang ramah lingkungan, terbuat dari limbah pertanian bonggol jagung yang kering. Proses pembuatannya meliputi pirolisis (pembakaran tanpa oksigen), penghalusan arang, pencampuran dengan bahan pengikat, pencetakan, dan pengeringan.",
            deskripsi = """
                Cara Membuat Arang Briket dari Pisang 🌽
                1. Persiapan: Bonggol jagung dikeringkan hingga benar-benar kering.
                2. Pirolisis: Bonggol kering dibakar dalam drum tertutup tanpa oksigen agar menjadi arang, bukan abu.
                3. Penghalusan: Arang yang dihasilkan dihancurkan menjadi serbuk halus. 
                4. Pencampuran: Serbuk arang dicampur dengan bahan pengikat seperti tepung kanji, ditambahkan air secukupnya hingga menjadi adonan.
                5. Pencetakan: Adonan dicetak sesuai bentuk yang diinginkan dengan tekanan yang cukup.
                6. Pengeringan: Briket yang sudah dicetak dijemur atau dikeringkan hingga benar-benar kering.
                
                Keunggulan 🌟
                1. Ramah Lingkungan: Memanfaatkan limbah pertanian.
                2. Ekonomis: Lebih murah dan mudah didapat.
                3. Stabil: Panas yang konsisten dan emisi rendah.
                
                Proses ini mengubah limbah bonggol jagung menjadi sumber energi alternatif yang efisien dan berkelanjutan!
                """.trimIndent(),
            tanggal = 20241025,
            kategori = "Pisang",
            duration = "03:35",
            photo = R.drawable.video_thumb,
            file_video = "https://drive.google.com/uc?export=download&id=1N6uujM7zrjK2e1ZBja6OEhflFKZlmHg8",
            author = "Willy Candra",
        ),
    )

    val artikelPembelajaran = listOf(
        Articles(
            id = 1,
            judul = "\uD83C\uDF3DTransformasi Bonggol Jagung Menjadi Briket Arang Berkualitas\uD83C\uDF31",
            deskripsi ="""
                Bonggol jagung sering dianggap sebagai limbah tak berguna dalam pertanian. Namun, limbah ini memiliki potensi besar untuk menjadi sumber energi alternatif yang efisien. Proses transformasi dimulai dengan mengeringkan bonggol jagung hingga benar-benar kering, memastikan kandungan air rendah. Setelah itu, bonggol jagung dibakar menggunakan metode pirolisis, yaitu pembakaran tanpa oksigen di dalam drum tertutup. Teknik ini menghasilkan arang berkualitas tinggi tanpa membakar habis materialnya.

                Arang yang sudah terbentuk kemudian dihancurkan menjadi serbuk halus. Proses ini dilakukan agar briket yang dihasilkan memiliki kepadatan dan kekuatan yang optimal. Serbuk arang tersebut dicampur dengan bahan pengikat, seperti tepung kanji, yang membantu menjaga bentuk dan struktur briket. Campuran ini dicetak menjadi briket dengan tekanan yang cukup agar padat, lalu dikeringkan di bawah sinar matahari atau menggunakan oven pengering. Tahap pengeringan ini memastikan briket kuat, tidak mudah hancur, dan siap digunakan.

                Briket arang dari bonggol jagung menawarkan berbagai manfaat. Selain memanfaatkan limbah pertanian yang melimpah, briket ini memiliki biaya produksi yang rendah dan menghasilkan panas yang stabil. Dibandingkan dengan bahan bakar konvensional, briket arang ini juga menghasilkan emisi yang lebih rendah, sehingga lebih ramah lingkungan. Transformasi bonggol jagung menjadi briket adalah langkah praktis yang mendukung ekonomi sirkular dan keberlanjutan energi, sekaligus menawarkan solusi hemat biaya bagi rumah tangga dan industri kecil.
                
                Anda bisa lihat disini cara membuat briket dari bongol jagung
            """.trimIndent(),
            tanggal = 20241025,
            kategori = "Jagung",
            photo = R.drawable.video_thumb,
            author = "Willy Candra",
        ),
        Articles(
            id = 2,
            judul = "Jerami Sebagai Sumber Bahan Organik Untuk Kesuburan Tanah",
            deskripsi = """
                Penurunan produktivitas sawah di Indonesia banyak disebabkan oleh degradasi kesuburan tanah, terutama karena rendahnya kandungan bahan organik. Saat ini, lebih dari 65% lahan sawah irigasi di Indonesia memiliki kandungan bahan organik kurang dari 2%, padahal standar kesuburan minimal adalah 3%. Salah satu penyebab utamanya adalah penggunaan pupuk kimia secara terus-menerus tanpa disertai pemupukan organik yang cukup. Kebiasaan membakar jerami setelah panen juga berkontribusi terhadap penurunan kesuburan tanah, karena menyebabkan hilangnya nutrisi penting seperti nitrogen, fosfor, dan kalium.

                Pembakaran jerami tidak hanya mengurangi kesuburan tanah, tetapi juga berdampak negatif pada kualitas udara dan kesehatan lingkungan. Selain itu, hilangnya bahan organik tanah berakibat pada berkurangnya mikroba yang penting bagi keseimbangan ekosistem lahan sawah. Oleh karena itu, pemanfaatan jerami sebagai pupuk organik sangat penting. Penggunaan jerami yang dikembalikan ke lahan sawah bisa membantu menjaga ketersediaan nutrisi, meningkatkan populasi mikroba tanah, dan memperbaiki struktur tanah.

                Pengolahan jerami menjadi kompos merupakan solusi ramah lingkungan yang efektif. Kompos jerami dapat menjadi alternatif yang lebih murah daripada pupuk anorganik dan membantu meningkatkan hasil panen. Kombinasi penggunaan pupuk organik dan anorganik memberikan hasil optimal, baik dalam hal produktivitas maupun kesuburan tanah jangka panjang. Langkah ini juga mendukung pertanian yang lebih berkelanjutan dan mengurangi ketergantungan pada pupuk kimia sintetis.
                
                 Anda bisa lihat disini cara membuat pupuk bokashi (Bahan Organik Kaya Sumber Hayati) dari jerami
            """.trimIndent(),
            tanggal = 20241025,
            kategori = "Jagung",
            photo = R.drawable.video_thumb,
            author = "Willy Candra",
        ),
        Articles(
            id = 3,
            judul = "Kompos Batang Pisang: Solusi Nutrisi Tanaman Alami",
            deskripsi = """
                It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. 
                
                Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
                
                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book
            """.trimIndent(),
            tanggal = 20241025,
            kategori = "Pisang",
            photo = R.drawable.video_thumb,
            author = "Willy Candra",
        ),
        Articles(
            id = 4,
            judul = "Jerami Jadi Bokashi: Pupuk Hayati Penyubur Tanah",
            deskripsi = """
                It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. 
                
                Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
                
                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book
            """.trimIndent(),
            tanggal = 20241025,
            kategori = "Jagung",
            photo = R.drawable.video_thumb,
            author = "Willy Candra",
        ),
        Articles(
            id = 5,
            judul = "Pemuda Serang Ubah Limbah Pelepah Pisang Jadi Sumber Ekonomi",
            deskripsi = """
                It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. 
                
                Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
                
                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book
            """.trimIndent(),
            tanggal = 20241025,
            kategori = "Pisang",
            photo = R.drawable.video_thumb,
            author = "Willy Candra",
        ),
        Articles(
            id = 6,
            judul = "Pemuda Serang Ubah Limbah Pelepah Pisang Jadi Sumber Ekonomi",
            deskripsi = """
                It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. 
                
                Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
                
                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book
            """.trimIndent(),
            tanggal = 20241025,
            kategori = "Pisang",
            photo = R.drawable.video_thumb,
            author = "Willy Candra",
        ),
        Articles(
            id = 7,
            judul = "Pemuda Serang Ubah Limbah Pelepah Pisang Jadi Sumber Ekonomi",
            deskripsi = """
                It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. 
                
                Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
                
                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book
            """.trimIndent(),
            tanggal = 20241025,
            kategori = "Pisang",
            photo = R.drawable.video_thumb,
            author = "Willy Candra",
        ),
        Articles(
            id = 8,
            judul = "Pemuda Serang Ubah Limbah Pelepah Pisang Jadi Sumber Ekonomi",
            deskripsi = """
                It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. 
                
                Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
                
                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book
            """.trimIndent(),
            tanggal = 20241025,
            kategori = "Pisang",
            photo = R.drawable.video_thumb,
            author = "Willy Candra",
        ),
        Articles(
            id = 9,
            judul = "Pemuda Serang Ubah Limbah Pelepah Pisang Jadi Sumber Ekonomi",
            deskripsi = """
                It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. 
                
                Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
                
                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book
            """.trimIndent(),
            tanggal = 20241025,
            kategori = "Pisang",
            photo = R.drawable.video_thumb,
            author = "Willy Candra",
        ),
        Articles(
            id = 10,
            judul = "Pemuda Serang Ubah Limbah Pelepah Pisang Jadi Sumber Ekonomi",
            deskripsi = """
                It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. 
                
                Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
                
                Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book
            """.trimIndent(),
            tanggal = 20241025,
            kategori = "Pisang",
            photo = R.drawable.video_thumb,
            author = "Willy Candra",
        ),

    )

    val weatherForecast = listOf(
        Weather(
            time = "01:00",
            temperature = 28,
            icon = R.drawable.sun
        ),
        Weather(
            time = "02:00",
            temperature = 28,
            icon = R.drawable.sun
        ),
        Weather(
            time = "03:00",
            temperature = 28,
            icon = R.drawable.sun
        ),
        Weather(
            time = "04:00",
            temperature = 28,
            icon = R.drawable.sun
        ),
        Weather(
            time = "05:00",
            temperature = 28,
            icon = R.drawable.sun
        ),
        Weather(
            time = "06:00",
            temperature = 28,
            icon = R.drawable.sun
        ),
        Weather(
            time = "07:00",
            temperature = 28,
            icon = R.drawable.sun
        ),
        Weather(
            time = "08:00",
            temperature = 28,
            icon = R.drawable.sun
        ),
        Weather(
            time = "09:00",
            temperature = 29,
            icon = R.drawable.sun
        ),
        Weather(
            time = "10:00",
            temperature = 30,
            icon = R.drawable.cloud
        ),
        Weather(
            time = "11:00",
            temperature = 32,
            icon = R.drawable.sun
        ),
        Weather(
            time = "12:00",
            temperature = 31,
            icon = R.drawable.rain
        ),
        Weather(
            time = "13:00",
            temperature = 30,
            icon = R.drawable.rain
        ),
        Weather(
            time = "14:00",
            temperature = 29,
            icon = R.drawable.cloud
        ),
        Weather(
            time = "15:00",
            temperature = 29,
            icon = R.drawable.cloud
        ),
        Weather(
            time = "16:00",
            temperature = 29,
            icon = R.drawable.cloud
        ),
        Weather(
            time = "17:00",
            temperature = 29,
            icon = R.drawable.cloud
        ),
        Weather(
            time = "18:00",
            temperature = 29,
            icon = R.drawable.cloud
        ),
        Weather(
            time = "19:00",
            temperature = 29,
            icon = R.drawable.cloud
        ),
        Weather(
            time = "20:00",
            temperature = 29,
            icon = R.drawable.cloud
        ),
        Weather(
            time = "21:00",
            temperature = 29,
            icon = R.drawable.cloud
        ),
        Weather(
            time = "22:00",
            temperature = 29,
            icon = R.drawable.cloud
        ),
        Weather(
            time = "23:00",
            temperature = 29,
            icon = R.drawable.cloud
        ),
        Weather(
            time = "00:00",
            temperature = 28,
            icon = R.drawable.sun
        ),
        Weather(
            time = "25:00",
            temperature = 28,
            icon = R.drawable.sun
        ),
        Weather(
            time = "26:00",
            temperature = 28,
            icon = R.drawable.sun
        ),
        Weather(
            time = "27:00",
            temperature = 28,
            icon = R.drawable.sun
        ),
    )

    val sampleComments = listOf(
        Comments(
            authorName = "Rofiul",
            authorImage = R.drawable.profil,
            content = "Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using",
            time = "7 jam"
        ),
        Comments(
            authorName = "Oka Wiyana",
            authorImage = R.drawable.profile_oka,
            content = "Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using",
            time = "8 jam"
        )
    )
}