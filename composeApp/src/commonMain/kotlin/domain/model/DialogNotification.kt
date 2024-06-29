package domain.model

data class DialogNotification(
    val bt1: String = "Хорошо",
    val bt2: String = "не сейчас",
    val title: String = "Разрешить доступ к push-уведомлениям?",
    val description: String = "Для того, чтобы не пропускать обновления, разрешите приложению отправлять вам push-уведомления.",
)