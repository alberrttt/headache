from PIL import Image
import os

# Load the sprite sheet
sprite_sheet = Image.open('./images/48x48trees.png')

# The sprite sheet is 192x48, so we have 4 trees of 48x48 each
sprite_width = 48
sprite_height = 48
num_sprites = 4

# Create images directory if it doesn't exist
os.makedirs('./images', exist_ok=True)

# Split the sprite sheet
for i in range(num_sprites):
    # Calculate the position to crop
    left = i * sprite_width
    top = 0
    right = left + sprite_width
    bottom = sprite_height
    
    # Crop the sprite
    sprite = sprite_sheet.crop((left, top, right, bottom))
    
    # Save the sprite
    sprite.save(f'./images/tree{i}.png')
    print(f'Saved tree{i}.png')

print('Done! Split 192x48 sprite sheet into 4 individual 48x48 tree images.')
