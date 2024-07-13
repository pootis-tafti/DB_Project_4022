document.addEventListener("DOMContentLoaded", () => {
  // Simulated user session data (will be replaced by actual backend session)
  let user = {
    isLoggedIn: false, 
    username: "",
    email: "",
    joined: "",
  };

  // Load Header
  fetch("./assets/components/header.html")
    .then((response) => response.text())
    .then((data) => {
      document.querySelector("header").innerHTML = data;
      updateNavLinks();
    });

  // Load Footer
  fetch("./assets/components/footer.html")
    .then((response) => response.text())
    .then((data) => {
      document.querySelector("footer").innerHTML = data;
    });

  // Update navigation links based on login state
  function updateNavLinks() {
    const navLinks = document.getElementById("nav-links");
    if (user.isLoggedIn) {
      navLinks.innerHTML = `
        <a href="profile.html" class="hover:underline ml-4">Profile</a>
        <button onclick="logout()" class="hover:underline ml-4">Logout</button>
      `;
    } else {
      navLinks.innerHTML = `
        <a href="login.html" class="hover:underline ml-4">Login</a>
        <a href="signup.html" class="hover:underline ml-4">Sign Up</a>
      `;
    }
  }

  // Handle create ad button click
  const createAdButton = document.getElementById("create-ad-button");
  const createAdPopup = document.getElementById("create-ad-popup");
  const cancelButton = document.getElementById("cancel-button");
  const createAdForm = document.getElementById("create-ad-form");

  createAdButton && createAdButton.addEventListener("click", () => {
    createAdPopup.classList.remove("hidden");
  });

  cancelButton && cancelButton.addEventListener("click", () => {
    createAdPopup.classList.add("hidden");
  });

  createAdForm && createAdForm.addEventListener("submit", (event) => {
    event.preventDefault();
    const adData = {
      title: document.getElementById("ad-title").value,
      description: document.getElementById("ad-description").value,
      image: document.getElementById("ad-image").value,
      price: document.getElementById("ad-price").value,
    };

    fetch("http://127.0.0.1:5000/create-ad", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(adData),
      credentials: "include",
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.message === "Ad created successfully") {
          alert("Ad created successfully!");
          createAdPopup.classList.add("hidden");
          // Optionally refresh the page or update the ad list
        } else {
          alert("Failed to create ad");
        }
      });
  });

  // Handle OTP login process
  const loginEmailForm = document.getElementById("login-email-form");
  const loginOtpForm = document.getElementById("login-otp-form");

  if (loginEmailForm) {
    loginEmailForm.addEventListener("submit", (event) => {
      event.preventDefault();
      const email = document.getElementById("login-email").value;

      fetch("http://127.0.0.1:5000/send-otp", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ email }),
        credentials: "include",
      })
        .then((response) => response.json())
        .then((data) => {
          if (data.message === "OTP sent successfully") {
            alert("OTP sent successfully. Please check your email.");
            loginEmailForm.classList.add("hidden");
            loginOtpForm.classList.remove("hidden");
          } else {
            alert("Failed to send OTP");
          }
        });
    });

    loginOtpForm.addEventListener("submit", (event) => {
      event.preventDefault();
      const otp = document.getElementById("login-otp").value;

      fetch("http://127.0.0.1:5000/verify-otp", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ otp }),
        credentials: "include",
      })
        .then((response) => response.json())
        .then((data) => {
          if (data.message === "Login successful") {
            user.isLoggedIn = true;
            updateNavLinks();
            window.location.href = "profile.html";
          } else {
            alert("Failed to verify OTP");
          }
        });
    });
  }

  // Handle OTP signup process
  const signupEmailForm = document.getElementById("signup-email-form");
  const signupOtpForm = document.getElementById("signup-otp-form");

  if (signupEmailForm) {
    signupEmailForm.addEventListener("submit", (event) => {
      event.preventDefault();
      const email = document.getElementById("signup-email").value;

      fetch("http://127.0.0.1:5000/send-otp", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ email }),
        credentials: "include",
      })
        .then((response) => response.json())
        .then((data) => {
          if (data.message === "OTP sent successfully") {
            alert("OTP sent successfully. Please check your email.");
            signupEmailForm.classList.add("hidden");
            signupOtpForm.classList.remove("hidden");
          } else {
            alert("Failed to send OTP");
          }
        });
    });

    signupOtpForm.addEventListener("submit", (event) => {
      event.preventDefault();
      const otp = document.getElementById("signup-otp").value;

      fetch("http://127.0.0.1:5000/verify-otp", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ otp }),
        credentials: "include",
      })
        .then((response) => response.json())
        .then((data) => {
          if (data.message === "Signup successful") {
            alert("Signup successful. Please log in.");
            window.location.href = "login.html";
          } else {
            alert("Failed to verify OTP");
          }
        });
    });
  }

  // Populate profile details if on profile page
  if (document.getElementById("profile-username")) {
    fetch("http://127.0.0.1:5000/profile", {
      credentials: "include",
    })
      .then((response) => {
        if (response.status === 401) {
          window.location.href = "login.html";
        } else {
          return response.json();
        }
      })
      .then((data) => {
        if (data) {
          user.username = data.username;
          user.email = data.email;
          user.joined = data.joined_date;
          document.getElementById("profile-username").textContent = user.username;
          document.getElementById("profile-email").textContent = user.email;
          document.getElementById("profile-joined").textContent = user.joined;
        }
      });
  }

});

// Simulate logout function
function logout() {
  fetch("http://127.0.0.1:5000/logout", {
    method: "POST",
    credentials: "include",
  })
    .then((response) => response.json())
    .then((data) => {
      if (data.message === "Logout successful") {
        alert("Logged out");
        window.location.href = "login.html";
      }
    });
}

document.addEventListener("DOMContentLoaded", () => {
  // Initial fetch and display ads
  fetchAds();

  // Handle filter form submission
  const filterForm = document.getElementById("filter-form");
  filterForm.addEventListener("submit", (event) => {
    event.preventDefault();
    applyFilters();
  });

  // Handle Elasticsearch input
  const elasticSearchInput = document.getElementById("elastic-search-input");
  elasticSearchInput.addEventListener("input", () => {
    const query = elasticSearchInput.value;
    searchElasticsearch(query);
  });

  // Function to fetch ads
  function fetchAds() {
    fetch("http://127.0.0.1:5000/recent-advertisements")
      .then(response => response.json())
      .then(ads => {
        displayAds(ads);
      });
  }

  // Function to display ads
  function displayAds(ads) {
    const adContainer = document.getElementById("ad-cards-container");
    adContainer.innerHTML = "";
    ads.forEach(ad => {
      const adCard = createAdCard(ad);
      adContainer.appendChild(adCard);
    });
  }

  // Function to apply filters
  function applyFilters() {
    const title = document.getElementById("filter-title").value.toLowerCase();
    const description = document.getElementById("filter-description").value.toLowerCase();
    const priceMin = document.getElementById("filter-price-min").value;
    const priceMax = document.getElementById("filter-price-max").value;

    fetch(`http://127.0.0.1:5000/filter-ads?title=${title}&description=${description}&priceMin=${priceMin}&priceMax=${priceMax}`)
      .then(response => response.json())
      .then(ads => {
        displayAds(ads);
      });
  }

  // Function to search using Elasticsearch
  function searchElasticsearch(query) {
    fetch(`http://127.0.0.1:5000/search-ads?query=${query}`)
      .then(response => response.json())
      .then(ads => {
        displayAds(ads);
      });
  }
});
