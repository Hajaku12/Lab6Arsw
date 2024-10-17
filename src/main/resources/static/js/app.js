// app.js
const App = (function () {

    let selectedAuthor = '';
    let blueprintsList = [];


    const setSelectedAuthor = function (authorName) {
        selectedAuthor = authorName;
        $("#selectedAuthor").text(selectedAuthor+"'s BluePrints");
    };


    const updateBlueprints = function (authorName) {
        
        apimock.getBlueprintsByAuthor(authorName, function (blueprints) {

            if (!blueprints || blueprints == null) {
                $("#blueprintsTable tbody").empty();
                $("#selectedAuthor").text("No found author selected");
                $("#totalPoints").text("0");
                return;
            }

            const transformedBlueprints = blueprints.map(bp => ({
                name: bp.name,
                points: bp.points
            }));

            blueprintsList = transformedBlueprints;
            $("#blueprintsTable tbody").empty();


            transformedBlueprints.forEach(bp => {

                $("#blueprintsTable tbody").append(
                    `<tr><td>${bp.name}</td><td>${bp.points.length}</td></tr>`
                );
            });

            const totalPoints = transformedBlueprints.reduce((sum, bp) => sum + bp.points.length, 0);
            $("#totalPoints").text(totalPoints);
        });
    };


    return {
        setSelectedAuthor,
        updateBlueprints
    };
})();


$(document).ready(function () {
    $("#queryButton").on("click", function () {
        document.getElementById("selectedAuthor").style.display=""
        const authorName = $("#authorName").val();
        document.getElementById("selectedAuthor").style.display="block"
        App.setSelectedAuthor(authorName);
        App.updateBlueprints(authorName);
    });
});
