header {
    background-color: #222831;
    padding-left: 20px;
    font-size: 25px;
    color: white;
    margin-bottom: 5%;
    display: grid;
    grid-template-columns: 1fr 5fr 1fr;
    align-items: center;
}
h2{
    margin-left: auto;
    margin-right: auto;
}
a:link, a:visited {
    background-color: #31363F;
    color: white;
    padding: 14px 25px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    max-width: fit-content;
    margin: 5px;
}
.wrapper {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
}
li {
    margin: 10px 0;
}
table {
    border-collapse: collapse;
    border: 1px solid #ddd;
}

th, td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
}

th {
    background-color: #f2f2f2;
}

tr:nth-child(even) {
    background-color: #f2f2f2;
}

tr:hover {
    background-color: #ddd;
}

fieldset {
    margin: 20px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f9f9f9;
}

legend {
    font-size: 1.2em;
    font-weight: bold;
    color: #333;
}

form {
    margin-top: 15px;
}

label {
    display: block;
    margin-bottom: 5px;
}

input[type="text"],
input[type="submit"] {
    width: 100%;
    padding: 8px;
    margin-bottom: 10px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 3px;
    font-size: 1em;
}

input[type="submit"] {
    background-color: #76ABAE;
    color: #fff;
    cursor: pointer;
}

input[type="submit"]:hover {
    background-color: #5d878a;
}