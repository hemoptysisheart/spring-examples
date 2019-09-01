$(document).ready(function () {
  console.log("jQuery ready!");

  $("#ping-bt").click(function (evt) {
    console.debug("evt", evt)
    alert("click!")
  });
});
