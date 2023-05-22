const versions = [
    "0.18.0",
    "0.17.0",
    "0.16.0",
    "0.15.0",
    "0.14.0",
    "0.13.0",
    "0.12.0",
    "0.11.0",
    "0.10.0",
    "0.9.0",
    "0.8.0",
    "0.7.0",
    "0.6.0",
    "0.6.0",
    "0.5.0",
    "0.4.0",
    "0.3.0",
    "0.2.0"
]
const latestVersion = versions[0];
const scriptTag = document.getElementById("versions-script");
const srcAttribute = scriptTag.getAttribute("src");
const versionPlaceholder = "Version"
var refPath;
var version;
if (srcAttribute.startsWith("..")) {
    refPath = scriptTag.getAttribute("src").match(/^(?:\.\.\/)+/)[0];
    version = scriptTag.dataset.version;
} else {
    refPath = "";
    version = versionPlaceholder;
}
if (version != versionPlaceholder && latestVersion != version) {
    window.addEventListener('load', () => {
        const remember = sessionStorage.getItem("remember-outdated-0.2.0");
        if (remember == null) {
            var outdatedWarningElement = document.getElementById("outdated-warning");
            if (outdatedWarningElement == null) {
                outdatedWarningElement  = document.createElement("div");
                outdatedWarningElement.id = "outdated-warning";
                document.body.prepend(outdatedWarningElement);
            }
            const closeAlertId = "outdated-warning__alert-close";
            outdatedWarningElement.innerHTML = `<div class="alert">
                    <span class="closebtn"><span class="hint">Don't show for the rest of the session &rarr; </span><span id="${closeAlertId}" onclick="rememberOutdated()">&times;</span></span>
                    <span class="info-icon"></span><span class="info">Version ${version} is outdated, latest version is <a href="${refPath}${latestVersion}">${latestVersion}</a>.</span>
                </div>`;
            const closeAlert = document.getElementById(closeAlertId);
            closeAlert.addEventListener('click', () => {
                sessionStorage.setItem(`remember-outdated-${version}`, true);
               outdatedWarningElement.classList.remove("show");
            });
            outdatedWarningElement.classList.add("show");
        }
    });
}
window.addEventListener('load', () => {
    const versionsElement = document.createElement("nav");
    versionsElement.id = "versions" ;
    versionsElement.innerHTML = `
        <button type="button">${version}<span class="arrow"></span></button>
        <ul class="dropdown">` +
            versions.filter(v => v != version).map(v => `<li><a href="${refPath}${v}/">${v}</a></li>`).join('\n') +
        `</ul>`;
    const libraryNameDiv = document.getElementsByClassName("library-name")[0];
    libraryNameDiv.after(versionsElement);
});