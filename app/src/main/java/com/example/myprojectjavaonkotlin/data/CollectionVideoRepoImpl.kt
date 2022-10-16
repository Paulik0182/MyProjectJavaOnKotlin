package com.example.myprojectjavaonkotlin.data

import com.example.myprojectjavaonkotlin.domain.entity.CollectionVideoEntity
import com.example.myprojectjavaonkotlin.domain.entity.VideoEntity
import com.example.myprojectjavaonkotlin.domain.repo.CollectionVideoRepo

class CollectionVideoRepoImpl : CollectionVideoRepo {

    private var data: MutableList<CollectionVideoEntity> = mutableListOf()

    override fun addCollectionVideo(collectionVideoEntity: CollectionVideoEntity) {
        data.add(collectionVideoEntity)
    }

    override fun getCollectionVideos(onCollectionVideo: (List<CollectionVideoEntity>) -> Unit) {
        onCollectionVideo(ArrayList(data))
    }

    override fun getVideo(videoId: Long, onVideo: (VideoEntity?) -> Unit) {
        getCollectionVideos {
            var result: VideoEntity? = null
            it.forEach { collection ->
               result = collection.video.find { video -> video.id == videoId }
                if (result != null){
                    onVideo.invoke(result)
                    return@getCollectionVideos
                }
            }
        }
    }

    init {
        data.add(
            CollectionVideoEntity(
                1,
                "Комедии",
                mutableListOf(
                    VideoEntity(
                        1,
                        "Любовь и голуби",
                        "Комедия",
                        "1984 год",
                        "Деревенская комедия - мелодрама «Любовь и голуби» повествует о непростых " +
                                "взаимоотношениях простых людей, которые не думают о том, что любовь сильная " +
                                "и вечная бывает не только в кино, но и они сами способны так любить. В центре " +
                                "сюжета будни деревенской семьи Василия Кузякина. Он живет простой обычной " +
                                "жизнью, есть у него и жена Надежда, женщина шумная и часто ругающая своего " +
                                "мужа за увлечение голубями.",
                        "https://4tololo.ru/sites/default/files/field/image/1_307_0.jpg"
                    ),
                    VideoEntity(
                        2,
                        "Тор",
                        "фантастика, боевик, приключения",
                        "2011 год",
                        "Тор прибывает в Асгард после серии тревожных слухов из дома. Там он узнаёт, " +
                                "что система управления замаскированного под Одина Локи приводит к интересным " +
                                "юридическим казусам — на волю выходит преступница Хель. Тор и Хель вступают в " +
                                "битву, которая заканчивается для протагониста крайне печально — его выбрасывают " +
                                "на планету Сакаар под управлением харизматичного, но хитрозадого Грандмастера.",
                        "https://i.pinimg.com/originals/c5/0c/f5/c50cf507a4254872c4a61980f815371d.png"
                    ),
                    VideoEntity(
                        4,
                        "Форсаж",
                        "приключения, триллер, боевик",
                        "2001 год",
                        "Криминальный боевик 2001 года, снятый режиссёром Робом Коэном по " +
                                "сценарию Гэри Скотта Томпсона, Дэвида Эйера и Эрика Бергквиста по сюжету " +
                                "Томпсона. Это первая часть франшизы «Форсаж», в которой снялись Пол Уокер " +
                                "в роли Брайана О’Коннора и Вин Дизель в роли Доминика Торетто, а также " +
                                "Мишель Родригес и Джордана Брюстер в ролях второго плана. По сюжету фильма, " +
                                "недавняя серия угонов автомобилей заставляет О’Коннора, офицера полиции," +
                                " работать под прикрытием и войти в доверие к Торетто, местному уличному " +
                                "гонщику, чтобы расследовать это дело.",
                        "https://filmatik.ru/uploads/movie/133648/backdrop/original/7sHktJV6gpffCVtuzb3l0YGmJu7.jpg"
                    )
                ),
            )
        )
        data.add(
            CollectionVideoEntity(
                2,
                "Фантастика",
                mutableListOf(
                    VideoEntity(
                        2,
                        "Тор",
                        "фантастика, боевик, приключения",
                        "2011 год",
                        "Тор прибывает в Асгард после серии тревожных слухов из дома. Там он узнаёт, " +
                                "что система управления замаскированного под Одина Локи приводит к интересным " +
                                "юридическим казусам — на волю выходит преступница Хель. Тор и Хель вступают в " +
                                "битву, которая заканчивается для протагониста крайне печально — его выбрасывают " +
                                "на планету Сакаар под управлением харизматичного, но хитрозадого Грандмастера.",
                        "https://i.pinimg.com/originals/c5/0c/f5/c50cf507a4254872c4a61980f815371d.png"
                    ),
                    VideoEntity(
                        3,
                        "Гонка",
                        "драма, биография",
                        "2013 год",
                        "Спортивно-историческая драма режиссёра Рона Ховарда[4]. Картина основана " +
                                "на реальных событиях, произошедших в чемпионате мира среди гонщиков " +
                                "«Формулы-1» сезона 1976 года. Получила две номинации на премию «Золотой " +
                                "глобус», в том числе как лучший драматический фильм года. Главные роли " +
                                "исполняют Крис Хемсворт и Даниэль Брюль.",
                        "https://images.kinorium.com/movie/shot/592301/w1500_49035124.jpg"
                    )
                ),
            )

        )
        data.add(
            CollectionVideoEntity(
                3,
                "Биография",
                mutableListOf(
                    VideoEntity(
                        3,
                        "Гонка",
                        "драма, биография",
                        "2013 год",
                        "Спортивно-историческая драма режиссёра Рона Ховарда[4]. Картина основана " +
                                "на реальных событиях, произошедших в чемпионате мира среди гонщиков " +
                                "«Формулы-1» сезона 1976 года. Получила две номинации на премию «Золотой " +
                                "глобус», в том числе как лучший драматический фильм года. Главные роли " +
                                "исполняют Крис Хемсворт и Даниэль Брюль.",
                        "https://images.kinorium.com/movie/shot/592301/w1500_49035124.jpg"
                    ),
                    VideoEntity(
                        6,
                        "Паук",
                        "триллер, драма",
                        "2002 год",
                        "Проведя 20 лет в доме для умалишенных, странный и нелюдимый Деннис " +
                                "Клег возвращается в мрачные закоулки Ист-Энда, где прошло его детство. " +
                                "Словно паук, он рыщет по паутине воспоминаний, опутавшей его больной " +
                                "разум, заново переживая боль и страх, ставшие его единственными друзьями " +
                                "в родном доме, больше похожем на ад.",
                        "https://kakogo-chisla.ru/wp-content/uploads/2022/07/den-CHeloveka-Pauka-kogda.jpg"
                    ),
                    VideoEntity(
                        4,
                        "Форсаж",
                        "приключения, триллер, боевик",
                        "2001 год",
                        "Криминальный боевик 2001 года, снятый режиссёром Робом Коэном по " +
                                "сценарию Гэри Скотта Томпсона, Дэвида Эйера и Эрика Бергквиста по сюжету " +
                                "Томпсона. Это первая часть франшизы «Форсаж», в которой снялись Пол Уокер " +
                                "в роли Брайана О’Коннора и Вин Дизель в роли Доминика Торетто, а также " +
                                "Мишель Родригес и Джордана Брюстер в ролях второго плана. По сюжету фильма, " +
                                "недавняя серия угонов автомобилей заставляет О’Коннора, офицера полиции," +
                                " работать под прикрытием и войти в доверие к Торетто, местному уличному " +
                                "гонщику, чтобы расследовать это дело.",
                        "https://filmatik.ru/uploads/movie/133648/backdrop/original/7sHktJV6gpffCVtuzb3l0YGmJu7.jpg"
                    )
                ),
            )
        )
        data.add(
            CollectionVideoEntity(
                4,
                "Приключения",
                mutableListOf(
                    VideoEntity(
                        4,
                        "Форсаж",
                        "приключения, триллер, боевик",
                        "2001 год",
                        "Криминальный боевик 2001 года, снятый режиссёром Робом Коэном по " +
                                "сценарию Гэри Скотта Томпсона, Дэвида Эйера и Эрика Бергквиста по сюжету " +
                                "Томпсона. Это первая часть франшизы «Форсаж», в которой снялись Пол Уокер " +
                                "в роли Брайана О’Коннора и Вин Дизель в роли Доминика Торетто, а также " +
                                "Мишель Родригес и Джордана Брюстер в ролях второго плана. По сюжету фильма, " +
                                "недавняя серия угонов автомобилей заставляет О’Коннора, офицера полиции," +
                                " работать под прикрытием и войти в доверие к Торетто, местному уличному " +
                                "гонщику, чтобы расследовать это дело.",
                        "https://filmatik.ru/uploads/movie/133648/backdrop/original/7sHktJV6gpffCVtuzb3l0YGmJu7.jpg"
                    ),
                    VideoEntity(
                        5,
                        "Терминатор",
                        "фантастика, боевик, триллер",
                        "1984 год",
                        "В центре сюжета — противостояние солдата и робота-терминатора, прибывших " +
                                "в 1984 год из постапокалиптического 2029 года. Цель терминатора: убить " +
                                "Сару Коннор — девушку, чей ещё нерождённый сын в возможном будущем " +
                                "выиграет войну человечества с машинами. Влюблённый в Сару солдат Кайл " +
                                "Риз пытается помешать терминатору. В фильме поднимаются проблемы " +
                                "путешествий во времени, судьбы, создания искусственного интеллекта, " +
                                "поведения людей в экстремальных ситуациях.",
                        "https://cdn.fishki.net/upload/post/2021/10/13/3973511/8e960630195e84955a249f5520ce34fe.png"
                    )
                ),
            )
        )
        data.add(
            CollectionVideoEntity(
                5,
                "Боевик",
                mutableListOf(
                    VideoEntity(
                        5,
                        "Терминатор",
                        "фантастика, боевик, триллер",
                        "1984 год",
                        "В центре сюжета — противостояние солдата и робота-терминатора, прибывших " +
                                "в 1984 год из постапокалиптического 2029 года. Цель терминатора: убить " +
                                "Сару Коннор — девушку, чей ещё нерождённый сын в возможном будущем " +
                                "выиграет войну человечества с машинами. Влюблённый в Сару солдат Кайл " +
                                "Риз пытается помешать терминатору. В фильме поднимаются проблемы " +
                                "путешествий во времени, судьбы, создания искусственного интеллекта, " +
                                "поведения людей в экстремальных ситуациях.",
                        "https://cdn.fishki.net/upload/post/2021/10/13/3973511/8e960630195e84955a249f5520ce34fe.png"
                    ),
                    VideoEntity(
                        8,
                        "Терминатор 2",
                        "фантастика, боевик, триллер",
                        "1987 год",
                        "В центре сюжета — противостояние солдата и робота-терминатора, прибывших " +
                                "в 1984 год из постапокалиптического 2029 года. Цель терминатора: убить " +
                                "Сару Коннор — девушку, чей ещё нерождённый сын в возможном будущем " +
                                "выиграет войну человечества с машинами. Влюблённый в Сару солдат Кайл " +
                                "Риз пытается помешать терминатору. В фильме поднимаются проблемы " +
                                "путешествий во времени, судьбы, создания искусственного интеллекта, " +
                                "поведения людей в экстремальных ситуациях.",
                        "https://www.factroom.ru/wp-content/uploads/2016/07/4.png"
                    ),
                    VideoEntity(
                        6,
                        "Паук",
                        "триллер, драма",
                        "2002 год",
                        "Проведя 20 лет в доме для умалишенных, странный и нелюдимый Деннис " +
                                "Клег возвращается в мрачные закоулки Ист-Энда, где прошло его детство. " +
                                "Словно паук, он рыщет по паутине воспоминаний, опутавшей его больной " +
                                "разум, заново переживая боль и страх, ставшие его единственными друзьями " +
                                "в родном доме, больше похожем на ад.",
                        "https://i.playground.ru/p/Iz2S3hrPRpqdskjhedg1DQ.jpeg"
                    )
                ),
            )
        )
        data.add(
            CollectionVideoEntity(
                6,
                "Триллер",
                mutableListOf(
                    VideoEntity(
                        6,
                        "Паук",
                        "триллер, драма",
                        "2002 год",
                        "Проведя 20 лет в доме для умалишенных, странный и нелюдимый Деннис " +
                                "Клег возвращается в мрачные закоулки Ист-Энда, где прошло его детство. " +
                                "Словно паук, он рыщет по паутине воспоминаний, опутавшей его больной " +
                                "разум, заново переживая боль и страх, ставшие его единственными друзьями " +
                                "в родном доме, больше похожем на ад.",
                        "https://kakogo-chisla.ru/wp-content/uploads/2022/07/den-CHeloveka-Pauka-kogda.jpg"
                    ),
                    VideoEntity(
                        4,
                        "Форсаж",
                        "приключения, триллер, боевик",
                        "2001 год",
                        "Криминальный боевик 2001 года, снятый режиссёром Робом Коэном по " +
                                "сценарию Гэри Скотта Томпсона, Дэвида Эйера и Эрика Бергквиста по сюжету " +
                                "Томпсона. Это первая часть франшизы «Форсаж», в которой снялись Пол Уокер " +
                                "в роли Брайана О’Коннора и Вин Дизель в роли Доминика Торетто, а также " +
                                "Мишель Родригес и Джордана Брюстер в ролях второго плана. По сюжету фильма, " +
                                "недавняя серия угонов автомобилей заставляет О’Коннора, офицера полиции," +
                                " работать под прикрытием и войти в доверие к Торетто, местному уличному " +
                                "гонщику, чтобы расследовать это дело.",
                        "https://filmatik.ru/uploads/movie/133648/backdrop/original/7sHktJV6gpffCVtuzb3l0YGmJu7.jpg"
                    )
                ),
            )
        )
    }
}