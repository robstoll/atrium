window.addEventListener('load', () => {
    darkModeSwitch()
})

const darkModeSwitch = () => {
    const localStorageKey = "dokka-dark-mode"
    const storage = localStorage.getItem(localStorageKey)
    const osDarkSchemePreferred = window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches
    const darkModeEnabled = storage ? JSON.parse(storage) : osDarkSchemePreferred
    const element = document.getElementById("theme-toggle-button")

    element.addEventListener('click', () => {
        const enabledClasses = document.getElementsByTagName("html")[0].classList
        enabledClasses.toggle("theme-dark")

        //if previously we had saved dark theme then we set it to light as this is what we save in local storage
        const darkModeEnabled = enabledClasses.contains("theme-dark")
        localStorage.setItem(localStorageKey, JSON.stringify(darkModeEnabled))
    })
}
