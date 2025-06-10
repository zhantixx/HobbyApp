package com.example.hobbyapp.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.room.Room
import com.example.hobbyapp.Data.AppDatabase
import com.example.hobbyapp.Domain.*
import com.example.hobbyapp.Data.FriendEntity
import com.example.hobbyapp.Data.PostEntity
import com.example.hobbyapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainVIewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)

    val friends: LiveData<List<FriendEntity>> = db.friendDao().getAll()
    val posts: LiveData<List<PostEntity>> = db.postDao().getAll()

    fun insertSamplePosts() {
        Log.d("InitCheck", "insertSamplePosts() вызван")

        viewModelScope.launch(Dispatchers.IO) {
            val count = db.postDao().getCount()
            if (count == 0) {
                val samples = listOf(
                    PostEntity(
                        title = "Parkta yoga",
                        description = "Tañerteñgi saat 8:00-de parkta yoga jasamaqpyn. Aua raiy jaqsy bolady dep kútilude! Qosylǵyn kelse, kel!",
                        imageResId = com.example.hobbyapp.R.drawable.yoga,
                    ),
                    PostEntity(
                        title = "Foto júrisi",
                        description = "Eski qala boyynsha fotoapparatpen júrip, surret túsiruge serik izdep jatyrmyn. Júrudi jáne fotosúrret túsirudi jaqsý kóretin adam kerek.",
                        imageResId = com.example.hobbyapp.R.drawable.photowalk,
                        authorName = "Aibek"
                    ),
                    PostEntity(
                        title = "Ójinde oıyndar keshini",
                        description = "Catan nemese UNO oıynyn oınaǵyn kelse, úıimde oıyn keshini úıymdastyryp jatyrmyn. Óz oıyndaryny al da kel!",
                        imageResId = com.example.hobbyapp.R.drawable.boardgames,
                        authorName = "Diana"
                    ),
                    PostEntity(
                        title = "Júgiris",
                        description = "Tañerteñ júgirip bastamaqpyn. 3 km-dan bastaimyz, bayau bólek, birge júgireik!",
                        imageResId = com.example.hobbyapp.R.drawable.running,
                        authorName = "Nursultan"
                    ),
                    PostEntity(
                        title = "Birge suret salaiq",
                        description = "Jeksenbi kúni art-kafede akvarelmen suret salamyn. Ónerdi jaqsý kóretin bolsań, qosyl!",
                        imageResId = com.example.hobbyapp.R.drawable.artmeet,
                        authorName = "Nursultan"
                    )
                )

                db.postDao().insertAll(samples)
            }
        }
    }
    fun getPostsByAuthor(name: String): LiveData<List<PostEntity>> {
        return db.postDao().getPostsByAuthor(name)
    }



    fun insertSampleFriends() {
        Log.d("InitCheck", "insertSampleFriends() вызван")

        viewModelScope.launch(Dispatchers.IO) {
            if (db.friendDao().getCount() == 0) {
                val friends = listOf(
                    FriendEntity(name = "Aibek", avatarResId = com.example.hobbyapp.R.drawable.avatar1),
                    FriendEntity(name = "Diana", avatarResId = com.example.hobbyapp.R.drawable.avatar2),
                    FriendEntity(name = "Nursultan", avatarResId = com.example.hobbyapp.R.drawable.avatar3)
                )

                db.friendDao().insertAll(friends)
        }
        }
    }
    fun deletePost(post: PostEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            db.postDao().delete(post)
        }
    }


    // Дополнительные LiveData (категории и жетекшилер)
    private val _category = MutableLiveData<MutableList<CategoryModel>>()
    private val _jetekshiler = MutableLiveData<MutableList<JetekshilerModel>>()

    val category: LiveData<MutableList<CategoryModel>> get() = _category
    val jetekshiler: LiveData<MutableList<JetekshilerModel>> get() = _jetekshiler

    fun loadCategory() {
        val lists = mutableListOf(
            CategoryModel(1, "Sport", R.drawable.sport),
            CategoryModel(2, "Suret salu", R.drawable.art),
            CategoryModel(3, "Bileu", R.drawable.ballet),
            CategoryModel(4, "Fotosuretter", R.drawable.camera)
        )
        _category.value = lists
    }

    fun loadJetelshlier() {
        val jetekshilerList = mutableListOf(
            JetekshilerModel(
                Address = "Astana, Qabanbay Batyr Ave 60, Fitness City",
                Biography = "Denesheny damytuga arnalgan trener",
                id = 1,
                Name = "Serik Amantai",
                Picture = R.drawable.teacher1,
                Experience = 4,
                Location = "Astana",
                Mobile = "87070001122",
                peoples = "80 oqushy",
                Rating = 4.2,
                schedule = listOf(
                    ScheduleModel(day = "Дүйсенбі", time = "08:00 - 10:00", subject = "Жүгіру"),
                    ScheduleModel(day = "Сәрсенбі", time = "10:00 - 12:00", subject = "Йога"),
                    ScheduleModel(day = "Жұма", time = "16:00 - 18:00", subject = "Футбол")
                ),
                Category = "Sport",
                Special = "Juzu"

            ),
            JetekshilerModel(
                Address = "Shymkent, Kunaev St 15, Boks Ortalygy",
                Biography = "Boks pen erte bastan ainalysady",
                id = 2,
                Name = "Nurzhan Esengeldi",
                Picture = R.drawable.teacher2,
                Experience = 6,
                Location = "Shymkent",
                Mobile = "87075556644",
                peoples = "110 oqushy",
                Rating = 4.7,
                schedule = listOf(
                    ScheduleModel(day = "Дүйсенбі", time = "08:00 - 10:00", subject = "Жүгіру"),
                    ScheduleModel(day = "Сәрсенбі", time = "10:00 - 12:00", subject = "Йога"),
                    ScheduleModel(day = "Жұма", time = "16:00 - 18:00", subject = "Футбол")
                ),                Category = "Sport",
                Special = "Boks"
            ),
            JetekshilerModel(
                Address = "Astana, Turan Ave 18, Aidos Fit Club",
                Biography = "Sport zattarymen jumu isteitin ustaz",
                id = 3,
                Name = "Aidos Maratuly",
                Picture = R.drawable.teacher3,
                Experience = 5,
                Location = "Astana",
                Mobile = "87076667788",
                peoples = "95 oqushy",
                Rating = 4.6,
                schedule = listOf(
                    ScheduleModel(day = "Дүйсенбі", time = "08:00 - 10:00", subject = "Жүгіру"),
                    ScheduleModel(day = "Сәрсенбі", time = "10:00 - 12:00", subject = "Йога"),
                    ScheduleModel(day = "Жұма", time = "16:00 - 18:00", subject = "Футбол")
                ),                Category = "Sport",
                Special = "Fitnes"
            ),
            JetekshilerModel(
                Address = "Kostanai, Al-Farabi Ave 25, Futbol Akademiyasy",
                Biography = "Balalar futbol komandasy treneri",
                id = 4,
                Name = "Nurlan Batyr",
                Picture = R.drawable.teacher4,
                Experience = 7,
                Location = "Kostanai",
                Mobile = "87074445566",
                peoples = "60 oqushy",
                Rating = 4.3,
                schedule = listOf(
                    ScheduleModel(day = "Дүйсенбі", time = "08:00 - 10:00", subject = "Жүгіру"),
                    ScheduleModel(day = "Сәрсенбі", time = "10:00 - 12:00", subject = "Йога"),
                    ScheduleModel(day = "Жұма", time = "16:00 - 18:00", subject = "Футбол")
                ),                Category = "Sport",
                Special = "Futbol"
            ),
            JetekshilerModel(
                Address = "Pavlodar, Toraigyrov St 40, Gimnastika Ortalygy",
                Biography = "Ushyp juru men gimnastika ustazy",
                id = 5,
                Name = "Dana Tulegenova",
                Picture = R.drawable.teacher5,
                Experience = 8,
                Location = "Pavlodar",
                Mobile = "87079990011",
                peoples = "100 oqushy",
                Rating = 4.9,
                schedule = listOf(
                    ScheduleModel(day = "Дүйсенбі", time = "08:00 - 10:00", subject = "Жүгіру"),
                    ScheduleModel(day = "Сәрсенбі", time = "10:00 - 12:00", subject = "Йога"),
                    ScheduleModel(day = "Жұма", time = "16:00 - 18:00", subject = "Футбол")
                ),                Category = "Sport",
                Special = "Gimnastika"
            ),
            JetekshilerModel(
                Address = "Astana, Dostyk St 14, Art Studio Aigerim"
                ,Biography = "Balalarǵa arnalǵan suret salu sabaktaryn ótedi",
                id = 6,
                Name = "Aigerim Tuleu",
                Picture = R.drawable.teacher6,
                Experience = 6,
                Location = "Astana",
                Mobile = "87073334455",
                peoples = "70 oqushy",
                Rating = 4.8,
                schedule = listOf(
                    ScheduleModel(day = "Сейсенбі", time = "13:00 - 15:00", subject = "Арт сабағы"),
                    ScheduleModel(day = "Бейсенбі", time = "15:00 - 17:00", subject = "Пейзаж"),
                    ScheduleModel(day = "Сенбі", time = "11:00 - 13:00", subject = "Портрет")
                ),
                Category = "Suret salu",
                Special = "Akvarel"
            ),
            JetekshilerModel(
                Address = "Almaty, Abay Ave 30, Dias Art School"
                ,Biography = "Qalammen suret salu ustazy",
                id = 7,
                Name = "Dias Erlanuly",
                Picture = R.drawable.teacher7,
                Experience = 5,
                Location = "Almaty",
                Mobile = "87075553322",
                peoples = "60 oqushy",
                Rating = 4.5,
                schedule = listOf(
                    ScheduleModel(day = "Сейсенбі", time = "13:00 - 15:00", subject = "Арт сабағы"),
                    ScheduleModel(day = "Бейсенбі", time = "15:00 - 17:00", subject = "Пейзаж"),
                    ScheduleModel(day = "Сенбі", time = "11:00 - 13:00", subject = "Портрет")
                ),                Category = "Suret salu",
                Special = "Grafika"
            ),
            JetekshilerModel(
                Address = "Shymkent, Respublika Ave 22, Zhibek Art Center"
                ,                Biography = "Balalar men jasóspirimderge arnalǵan kómekshi",
                id = 8,
                Name = "Zhibek Nurzhan",
                Picture = R.drawable.teacher8,
                Experience = 3,
                Location = "Shymkent",
                Mobile = "87071112233",
                peoples = "85 oqushy",
                Rating = 4.3,
                schedule = listOf(
                    ScheduleModel(day = "Сейсенбі", time = "13:00 - 15:00", subject = "Арт сабағы"),
                    ScheduleModel(day = "Бейсенбі", time = "15:00 - 17:00", subject = "Пейзаж"),
                    ScheduleModel(day = "Сенбі", time = "11:00 - 13:00", subject = "Портрет")
                ),
                Category = "Suret salu",
                Special = "Portret"
            ),
            JetekshilerModel(
                Address = "Taraz, Tole Bi St 19, Samat Studio"
                ,                Biography = "Suret arqyly balanyń ózindik pikirin ashady",
                id = 9,
                Name = "Samat Karatay",
                Picture = R.drawable.teacher9,
                Experience = 7,
                Location = "Taraz",
                Mobile = "87076668899",
                peoples = "90 oqushy",
                Rating = 4.6,
                schedule = listOf(
                    ScheduleModel(day = "Сейсенбі", time = "13:00 - 15:00", subject = "Арт сабағы"),
                    ScheduleModel(day = "Бейсенбі", time = "15:00 - 17:00", subject = "Пейзаж"),
                    ScheduleModel(day = "Сенбі", time = "11:00 - 13:00", subject = "Портрет")
                ),                Category = "Suret salu",
                Special = "Peizazh"
            ),
            JetekshilerModel(
                Address = "Kyzylorda, Aiteke Bi St 5, Madina Digital Art"
                ,                Biography = "Digital art pen grafikalardy úiretedi",
                id = 10,
                Name = "Madina Zhanserik",
                Picture = R.drawable.teacher10,
                Experience = 4,
                Location = "Kyzylorda",
                Mobile = "87072225566",
                peoples = "50 oqushy",
                Rating = 4.4,
                schedule = listOf(
                    ScheduleModel(day = "Сейсенбі", time = "13:00 - 15:00", subject = "Арт сабағы"),
                    ScheduleModel(day = "Бейсенбі", time = "15:00 - 17:00", subject = "Пейзаж"),
                    ScheduleModel(day = "Сенбі", time = "11:00 - 13:00", subject = "Портрет")
                ),                Category = "Suret salu",
                Special = "Sanly óner"
            ),
            JetekshilerModel(
                Address = "Almaty, Al-Farabi Ave 77, Kamila Ballet Studio"
                ,                Biography = "Balalarǵa klassikalyq bileudi úiretedi",
                id = 11,
                Name = "Kamila Nurtai",
                Picture = R.drawable.teacher11,
                Experience = 6,
                Location = "Almaty",
                Mobile = "87078889900",
                peoples = "95 oqushy",
                Rating = 4.9,
                schedule = listOf(
                    ScheduleModel(day = "Дүйсенбі", time = "09:00 - 11:00", subject = "Балет"),
                    ScheduleModel(day = "Сәрсенбі", time = "12:00 - 14:00", subject = "Модерн"),
                    ScheduleModel(day = "Жұма", time = "10:00 - 12:00", subject = "Хип-хоп")
                ),
                Category = "Bileu",
                Special = "Klassikalyq bileu"
            ),
            JetekshilerModel(
                Address = "Astana, Saryarka Ave 12, Modern Dance Studio"
                ,                Biography = "Zamanaui bileu múǵalimi",
                id = 12,
                Name = "Adilbek Asan",
                Picture = R.drawable.teacher12,
                Experience = 5,
                Location = "Astana",
                Mobile = "87074445522",
                peoples = "100 oqushy",
                Rating = 4.7,
                schedule = listOf(
                    ScheduleModel(day = "Дүйсенбі", time = "09:00 - 11:00", subject = "Балет"),
                    ScheduleModel(day = "Сәрсенбі", time = "12:00 - 14:00", subject = "Модерн"),
                    ScheduleModel(day = "Жұма", time = "10:00 - 12:00", subject = "Хип-хоп")
                ),                Category = "Bileu",
                Special = "Modern bileu"
            ),
            JetekshilerModel(
                Address = "Aktobe, Zhubanov St 33, Dana Hip-Hop Hall"
                ,                Biography = "Jasár balalarǵa hip-hop kúntarlaryn ótedi",
                id = 13,
                Name = "Dana Ermekkyzy",
                Picture = R.drawable.teacher13,
                Experience = 4,
                Location = "Aktobe",
                Mobile = "87079998877",
                peoples = "80 oqushy",
                Rating = 4.5,
                schedule = listOf(
                    ScheduleModel(day = "Дүйсенбі", time = "09:00 - 11:00", subject = "Балет"),
                    ScheduleModel(day = "Сәрсенбі", time = "12:00 - 14:00", subject = "Модерн"),
                    ScheduleModel(day = "Жұма", time = "10:00 - 12:00", subject = "Хип-хоп")
                ),                Category = "Bileu",
                Special = "Hip-hop"
            ),
            JetekshilerModel(
                Address = "Semey, Abai St 50, Sultan Choreography Hall"
                ,                Biography = "Xoreografiya boyynsha kóp jylǵy tájiribesi bar",
                id = 14,
                Name = "Sultan Beksultan",
                Picture = R.drawable.teacher14,
                Experience = 8,
                Location = "Semey",
                Mobile = "87076669988",
                peoples = "120 oqushy",
                Rating = 4.9,
                schedule = listOf(
                    ScheduleModel(day = "Дүйсенбі", time = "09:00 - 11:00", subject = "Балет"),
                    ScheduleModel(day = "Сәрсенбі", time = "12:00 - 14:00", subject = "Модерн"),
                    ScheduleModel(day = "Жұма", time = "10:00 - 12:00", subject = "Хип-хоп")
                ),                Category = "Bileu",
                Special = "Xoreografiya"
            ),
            JetekshilerModel(
                Address = "Kostanai, Baitursynov St 18, Aruzhan Ballet School"
                ,                Biography = "Balet múǵalimi, konkurs jénimpazy",
                id = 15,
                Name = "Aruzhan Mukhtar",
                Picture = R.drawable.teacher15,
                Experience = 6,
                Location = "Kostanai",
                Mobile = "87071110099",
                peoples = "75 oqushy",
                Rating = 4.6,
                schedule = listOf(
                    ScheduleModel(day = "Дүйсенбі", time = "09:00 - 11:00", subject = "Балет"),
                    ScheduleModel(day = "Сәрсенбі", time = "12:00 - 14:00", subject = "Модерн"),
                    ScheduleModel(day = "Жұма", time = "10:00 - 12:00", subject = "Хип-хоп")
                ),                Category = "Bileu",
                Special = "Balet"
            ),
            JetekshilerModel(
                Address = "Almaty, Satpayev St 45, Aruzhan Photo School"
                ,                Biography = "Balalar men jasóspirimderge fotósuret túsirudi úiretedi",
                id = 16,
                Name = "Aruzhan Sadyk",
                Picture = R.drawable.teacher16,
                Experience = 5,
                Location = "Almaty",
                Mobile = "87075551234",
                peoples = "65 oqushy",
                Rating = 4.7,
                schedule = listOf(
                    ScheduleModel(day = "Сейсенбі", time = "14:00 - 16:00", subject = "Портрет түсіру"),
                    ScheduleModel(day = "Бейсенбі", time = "11:00 - 13:00", subject = "Табиғат фото"),
                    ScheduleModel(day = "Сенбі", time = "16:00 - 18:00", subject = "Фотостудия")
                ),
                Category = "Fotosuretter",
                Special = "Portret fotosuretter"
            ),
            JetekshilerModel(
                Address = "Astana, Mangilik El Ave 10, Temirlan Photo Studio"
                ,                Biography = "Sanly kamera men redaktor qoldanudy úiretedi",
                id = 17,
                Name = "Temirlan Amanbek",
                Picture = R.drawable.teacher17,
                Experience = 4,
                Location = "Astana",
                Mobile = "87076663456",
                peoples = "70 oqushy",
                Rating = 4.5,
                schedule = listOf(
                    ScheduleModel(day = "Сейсенбі", time = "14:00 - 16:00", subject = "Портрет түсіру"),
                    ScheduleModel(day = "Бейсенбі", time = "11:00 - 13:00", subject = "Табиғат фото"),
                    ScheduleModel(day = "Сенбі", time = "16:00 - 18:00", subject = "Фотостудия")
                ),                Category = "Fotosuretter",
                Special = "Sanly foto"
            ),
            JetekshilerModel(
                Address = "Karaganda, Bukhar-Zhyrau Ave 28, Assel Photo Center"
                ,                Biography = "Tájiribeli fotográf, túrkí serialderde jumys istegen",
                id = 18,
                Name = "Assel Zhanibek",
                Picture = R.drawable.teacher18,
                Experience = 7,
                Location = "Karaganda",
                Mobile = "87074442345",
                peoples = "90 oqushy",
                Rating = 4.8,
                schedule = listOf(
                    ScheduleModel(day = "Сейсенбі", time = "14:00 - 16:00", subject = "Портрет түсіру"),
                    ScheduleModel(day = "Бейсенбі", time = "11:00 - 13:00", subject = "Табиғат фото"),
                    ScheduleModel(day = "Сенбі", time = "16:00 - 18:00", subject = "Фотостудия")
                ),                Category = "Fotosuretter",
                Special = "Kúndelikti fotósúretti túsiru"
            ),
            JetekshilerModel(
                Address = "Shymkent, Tauke Khan Ave 7, Bauyrzhan Nature Photo Club"
                ,                Biography = "Tabigat suretterin túsiruge arnalg'an kúrsatushy",
                id = 19,
                Name = "Bauyrzhan Kuatuly",
                Picture = R.drawable.teacher19,
                Experience = 6,
                Location = "Shymkent",
                Mobile = "87079994411",
                peoples = "85 oqushy",
                Rating = 4.6,
                schedule = listOf(
                    ScheduleModel(day = "Сейсенбі", time = "14:00 - 16:00", subject = "Портрет түсіру"),
                    ScheduleModel(day = "Бейсенбі", time = "11:00 - 13:00", subject = "Табиғат фото"),
                    ScheduleModel(day = "Сенбі", time = "16:00 - 18:00", subject = "Фотостудия")
                ),                Category = "Fotosuretter",
                Special = "Peizaj"
            ),
            JetekshilerModel(
                Address = "Petropavl, Pushkin St 35, Zarina Photo Studio"
                ,                Biography = "Fotosuretterdi kompoziciya men júyeli túsiru ustazy",
                id = 20,
                Name = "Zarina Moldir",
                Picture = R.drawable.teacher20,
                Experience = 3,
                Location = "Petropavl",
                Mobile = "87070009988",
                peoples = "50 oqushy",
                Rating = 4.4,
                schedule = listOf(
                    ScheduleModel(day = "Сейсенбі", time = "14:00 - 16:00", subject = "Портрет түсіру"),
                    ScheduleModel(day = "Бейсенбі", time = "11:00 - 13:00", subject = "Табиғат фото"),
                    ScheduleModel(day = "Сенбі", time = "16:00 - 18:00", subject = "Фотостудия")
                ),                Category = "Fotosuretter",
                Special = "Kompoziciya"
            )
        )

        _jetekshiler.value = jetekshilerList
    }

    private val _filteredJetekshiler = MutableLiveData<List<JetekshilerModel>>()
    val filteredJetekshiler: LiveData<List<JetekshilerModel>> get() = _filteredJetekshiler

    fun filterJetekshiler(query: String) {
        val originalList = _jetekshiler.value ?: return
        _filteredJetekshiler.value = if (query.isBlank()) originalList else {
            originalList.filter {
                it.Name.contains(query, ignoreCase = true) ||
                        it.Special.contains(query, ignoreCase = true) ||
                        it.Category.contains(query, ignoreCase = true)
            }
        }
    }
}
