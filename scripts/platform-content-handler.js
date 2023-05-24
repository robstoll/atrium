function osDarkSchemePreferred(){
    return window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches;
}
const localStorageKey = "dokka-dark-mode";
const storage = localStorage.getItem(localStorageKey)
const htmlElement = document.getElementsByTagName("html")[0];
if (storage !== null && JSON.parse(storage) === true ||
    storage == null && osDarkSchemePreferred() === true
) {
    htmlElement.classList.add("theme-dark");
}

window.addEventListener('load', () => {
    const toggleButton = document.getElementById("theme-toggle-button")
    toggleButton.addEventListener('click', () => {
        const enabledClasses = htmlElement.classList;
        enabledClasses.toggle("theme-dark");

        //if previously we had saved dark theme then we set it to light as this is what we save in local storage
        const darkModeEnabled = enabledClasses.contains("theme-dark");
        localStorage.setItem(localStorageKey, JSON.stringify(darkModeEnabled));
    });
});