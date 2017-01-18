function getWelcomeMessage() {
    var hours = new Date().getHours();

    if (hours < 6) {
        return "Доброй ночи";
    }
    if (hours < 10) {
        return "Доброе утро";
    }
    if (hours < 18) {
        return "Добрый день";
    }
    if (hours < 22) {
        return "Добрый вечер";
    }
    else {
        return "Доброй ночи";
    }
}
document.write(getWelcomeMessage());