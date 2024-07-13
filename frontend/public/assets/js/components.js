// Component for Ad Card
export function createAdCard(ad) {
    const adCard = document.createElement('div');
    adCard.className = 'border rounded shadow-lg p-4 bg-white';
  
    const img = document.createElement('img');
    img.src = ad.image;
    img.alt = ad.title;
    img.className = 'w-full h-48 object-cover mb-4';
    adCard.appendChild(img);
  
    const title = document.createElement('h2');
    title.className = 'text-xl font-bold mb-2';
    title.innerText = ad.title;
    adCard.appendChild(title);
  
    const description = document.createElement('p');
    description.className = 'text-gray-700 mb-4';
    description.innerText = ad.description;
    adCard.appendChild(description);
  
    const price = document.createElement('p');
    price.className = 'text-primary font-bold';
    price.innerText = `${ad.price} تومان`;
    adCard.appendChild(price);
  
    return adCard;
  }
  