package npsm.study.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform