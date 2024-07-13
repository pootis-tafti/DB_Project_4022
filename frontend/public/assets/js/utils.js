async function fetchAds() {
    const response = await fetch('/api/ads');
    const data = await response.json();
    return data;
  }
  