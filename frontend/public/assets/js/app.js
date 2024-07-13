document.addEventListener("DOMContentLoaded", () => {

  // this array should be empty for real fetch requests
  let allAds = [
    {
      title: "Ad 1",
      description: "This is a description for ad 1.",
      image: "https://via.placeholder.com/150",
      price: "210000",
    },
    {
      title: "Ad 2",
      description: "This is a description for ad 2.",
      image: "https://via.placeholder.com/150",
      price: "240000",
    },
    {
      title: "Ad 3",
      description: "This is a description for ad 3.",
      image: "https://via.placeholder.com/150",
      price: "140000",
    },
  ];

  fetchAds();

  const filterForm = document.getElementById("filter-form");
  filterForm.addEventListener("submit", (event) => {
    event.preventDefault();
    applyFilters();
  });

  const elasticSearchInput = document.getElementById("elastic-search-input");
  elasticSearchInput.addEventListener("input", () => {
    const query = elasticSearchInput.value;
    searchElasticsearch(query);
  });

  function fetchAds() {
    // uncomment the following code for fetching the real data

    // fetch("http://127.0.0.1:5000/recent-advertisements")
    //   .then((response) => response.json())
    //   .then((ads) => {
    //     allAds = ads;
        displayAds(allAds);
      // });
  }

  function displayAds(ads) {
    const adContainer = document.getElementById("ad-cards-container");
    adContainer.innerHTML = "";
    ads.forEach((ad) => {
      const adCard = createAdCard(ad);
      adContainer.appendChild(adCard);
    });
  }

  function applyFilters() {
    const title = document.getElementById("filter-title").value.toLowerCase();
    const description = document.getElementById("filter-description").value.toLowerCase();
    const priceMin = document.getElementById("filter-price-min").value;
    const priceMax = document.getElementById("filter-price-max").value;

    const filteredAds = allAds.filter((ad) => {
      return (
        (title === "" || ad.title.toLowerCase().includes(title)) &&
        (description === "" || ad.description.toLowerCase().includes(description)) &&
        (priceMin === "" || parseInt(ad.price) >= parseInt(priceMin)) &&
        (priceMax === "" || parseInt(ad.price) <= parseInt(priceMax))
      );
    });

    displayAds(filteredAds);
  }

  function searchElasticsearch(query) {
    fetch(`http://127.0.0.1:5000/search-ads?query=${query}`)
      .then((response) => response.json())
      .then((ads) => {
        displayAds(ads);
      });
  }

  function createAdCard(ad) {
    const adCard = document.createElement("div");
    adCard.className = "border rounded shadow-lg p-4 bg-white";

    const img = document.createElement("img");
    img.src = ad.image;
    img.alt = ad.title;
    img.className = "w-full h-48 object-cover mb-4";
    adCard.appendChild(img);

    const title = document.createElement("h2");
    title.className = "text-xl font-bold mb-2";
    title.innerText = ad.title;
    adCard.appendChild(title);

    const description = document.createElement("p");
    description.className = "text-gray-700 mb-4";
    description.innerText = ad.description;
    adCard.appendChild(description);

    const price = document.createElement("p");
    price.className = "text-primary font-bold";
    price.innerText = `${ad.price} تومان`;
    adCard.appendChild(price);

    return adCard;
  }
});
