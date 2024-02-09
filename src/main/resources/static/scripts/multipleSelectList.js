//const btn = document.querySelector(".btn-select");
//const items = document.querySelectorAll(".item");
//const checks = document.querySelectorAll(".check");
//
//btn.addEventListener("click", () => {
//  btn.classList.toggle("open");
//});
//
//items.forEach((item, index) => {
//  item.addEventListener("click", () => {
//    checks[index].checked = !checks[index].checked;
//
//    updateButtonText();
//  });
//});
//
//function updateButtonText() {
//  let btnText = document.querySelector(".btn-text");
//  let checked = document.querySelectorAll(".check:checked");
//
//  if (checked && checked.length > 0) {
//    btnText.innerText = `Wybrano: ${checked.length}`;
//  } else {
//    btnText.innerText = "Wybierz z listy";
//  }
//}

const containerSelectors = [".container1", ".container2"];
const btns = document.querySelectorAll(".btn-select");
const checksLists = document.querySelectorAll(".check-list");

btns.forEach((btn, btnIndex) => {
  btn.addEventListener("click", () => {
    btn.classList.toggle("open");
  });

  const items = container.querySelector(".item");
  const checks = checksLists[btnIndex].querySelectorAll(".check");

  items.forEach((item, index) => {
    item.addEventListener("click", () => {
      checks[index].checked = !checks[index].checked;

      updateButtonText(btnIndex);
    });
  });
});

function updateButtonText(btnIndex) {
  const btnText = btns[btnIndex].querySelector(".btn-text");
  const checked = checksLists[btnIndex].querySelectorAll(".check:checked");

  if (checked && checked.length > 0) {
    btnText.innerText = `Wybrano: ${checked.length}`;
  } else {
    btnText.innerText = "Wybierz z listy";
  }
}
