function extractBody() {
  var role = Array.from(document.querySelectorAll("input[name='role']"))
    .filter(x => x.checked === true)
    .at(0)
    .dataset.value;

  return {
    id: Number(document.getElementById("userId")?.value),
    emailAddress: document.getElementById("email").value,
    password: document.getElementById("password").value,
    name: document.getElementById("fullName").value,
    phoneNumber: document.getElementById("phone").value,
    role: JSON.parse(role),
  };
}

function register() {
  fetch("/api/users/register", {
    method: "POST",
    body: JSON.stringify(extractBody()),
    headers: {
      "Content-type": "application/json; charset=UTF-8"
    }
  })
    .then(_ => window.location.href = '/login');
}

function save() {
  fetch("/api/users", {
    method: "PUT",
    body: JSON.stringify(extractBody()),
    headers: {
      "Content-type": "application/json; charset=UTF-8"
    }
  })
    .then(res => {
      if (res.status !== 200) {
        window.location.href = '/user-profile?error';
      }
      else {
        window.location.href = '/user-profile?success';
      }
    });
}
