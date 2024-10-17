//@author hcadavid

apimock = (function () {

    var mockdata = [];

    mockdata["johnconnor"] = [
        { author: "johnconnor", "points": [{ "x": 150, "y": 120 }, { "x": 215, "y": 115 }], "name": "house" },
        { author: "johnconnor", "points": [{ "x": 340, "y": 240 }, { "x": 15, "y": 215 }], "name": "gear" }
    ];
    mockdata["maryweyland"] = [
        { author: "maryweyland", "points": [{ "x": 140, "y": 140 }, { "x": 115, "y": 115 }], "name": "house2" },
        { author: "maryweyland", "points": [{ "x": 140, "y": 140 }, { "x": 115, "y": 115 }], "name": "gear2" }
    ];

    mockdata["shapes"] = [
        {
            author: "shapes", "points": [
                { "x": 110, "y": 110 },
                { "x": 210, "y": 110 },
                { "x": 160, "y": 210 },
                { "x": 110, "y": 110 }
            ], "name": "triangle"
        },
        {
            author: "shapes", "points": [
                { "x": 110, "y": 110 },
                { "x": 210, "y": 110 },
                { "x": 210, "y": 210 },
                { "x": 110, "y": 210 },
                { "x": 110, "y": 110 }
            ], "name": "square"
        },
        {
            author: "shapes", "points": [
                { "x": 110, "y": 90 },
                { "x": 210, "y": 90 },
                { "x": 230, "y": 170 },
                { "x": 160, "y": 210 },
                { "x": 90, "y": 170 },
                { "x": 110, "y": 90 }
            ], "name": "pentagon"
        }
    ];

    mockdata["figures"] = [
        {
            author: "figures", "points": [
                { "x": 110, "y": 60 },
                { "x": 160, "y": 210 },
                { "x": 210, "y": 60 },
                { "x": 80, "y": 150 },
                { "x": 230, "y": 150 },
                { "x": 110, "y": 60 }
            ], "name": "star"
        },
        {
            author: "figures", "points": [
                { "x": 110, "y": 60 },
                { "x": 210, "y": 60 },
                { "x": 210, "y": 160 },
                { "x": 160, "y": 210 },
                { "x": 110, "y": 160 },
                { "x": 110, "y": 60 },
                { "x": 210, "y": 160 },
                { "x": 110, "y": 160 },
                { "x": 210, "y": 60 }
            ], "name": "house"
        }
    ];

    return {
        getBlueprintsByAuthor: function (authname, callback) {
            if (mockdata.hasOwnProperty(authname)) {
				callback(mockdata[authname]);
			} else {
				callback(null); // o callback([]);
			}
        },

        getBlueprintsByNameAndAuthor: function (authname, bpname, callback) {
            callback(
                mockdata[authname].find(function (e) { return e.name === bpname })
            );
        }
    }

})();

/*
Example of use:
var fun = function (list) {
    console.info(list);
}

apimock.getBlueprintsByAuthor("johnconnor", fun);
apimock.getBlueprintsByNameAndAuthor("johnconnor", "house", fun);
*/