package com.example.gardenry;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Composting extends AppCompatActivity {
TextView tv1,tv2,tv3,tv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composting);
        tv2=findViewById(R.id.bodytext2);
        tv3=findViewById(R.id.bodytext3);
        tv1=findViewById(R.id.bodytext);
        tv4=findViewById(R.id.bodytext4);

tv1.setText("The urban Indian citizen generates nearly 700 grams of solid waste per person per day which is nearly 250 kg in a year.\n" +
        "More than half of what we carelessly throw into the trash is organic matter, which if composted, can produce rich top soil for our plants. Unfortunately most of us do not segregate our dry waste from wet waste, which makes composting impossible. The precious wet waste — what can potentially become black gold — remains unusable junk inside our landfills.\n" +
        "Also, by mixing our food waste with our recyclable waste (paper, plastic, metal), we make even our recyclable waste less recoverable.\n" +
        "If we pass on this responsibility to the Municipality without source segregation, we recover abysmally low levels of value from our trash. Due to poor source segregation, Municipalities in India are currently able to compost only 0.21 % of the wet waste we throw away.\n" +
        "The key to a clean, garbage free city lies in citizens doing their civic duty of source segregation and composting.");

tv2.setText("Composting is simply the process of breaking down the organic matter (food waste) in the presence of air and water, using micro organisms and small insects present in nature. The end product is called compost which is rich in readily usable plant nutrients forming a part of healthy soil.\n" +
        "Composting organisms require 4 conditions to create compost:\n" +
        "Carbon that comes from brown organic matter like dried leaves, sawdust, paper\n" +
        "Nitrogen that comes from fruit and vegetable waste, coffee grounds\n" +
        "Oxygen which comes from air\n" +
        "Water in the right amounts\n" +
        "Landfills are not the ideal environment to create compost, since food waste is made toxic by the plastic and metal waste. Further waste gets piled up everyday like a mountain and the layers below are cut off from oxygen\n" +
        "\n"+
        " Easy steps to compost your kitchen waste\n" +
        "Separate your edible kitchen waste (vegetable peels, fruit peels, small amounts of wasted cooked food) in a container\n" +
        "Collect dry organic matter (dried leaves, sawdust) in a small container\n" +
        "Take a large earthen pot or a bucket and drill 4 – 5 holes around the container at different levels to let air inside.\n" +
        "Line the bottom with a layer of soil.\n" +
        "Now start adding food waste in layers alternating wet waste (food scraps, vegetable and fruit peels) with dry waste (straw, sawdust, dried leaves).\n" +
        "Cover this container with a plastic sheet or a plank of wood to help retain moisture and heat.\n" +
        "Every few days, use a rake to give the pile a quick turn to provide aeration. If you think the pile is too dry, sprinkle some water so that it is moist.\n" +
        "Within 2 - 3 months, your pile should start forming compost that is dry, dark brown and crumbly and smelling of earth. There are also readymade composting kits available for those who want to overcome initial resistance to starting composting.\n" +
        "With time and a little patience, composting will become second nature to you.\n" +
        "By segregating, recycling and composting, a family of 4 can reduce their waste from 1000 Kg to less than 100 kg every year. Imagine 90% of all the garbage in Chennai vanishing overnight and a clean, green city- it will help you start your composting journey.");

tv3.setText("Compost is very useful! It can be mixed into normal soil to help plants, flowers and crops grow faster and stronger. Instead of buying fertiliser, we can make it for free at home or at school.\n" +
        "Compost also helps our planet to stay safe and clean. How’s that? Let’s look at happens when we don’t compost.\n" +
        "Normally, people will throw their food scraps and other organic rubbish into the trash and it ends up in a very different, and much, much bigger pile called a “landfill”. A landfill is a giant pile of trash that will never break down. This is because it contains both organic and inorganic trash like plastic which cannot decompose naturally.\n" +
        "Landfills can be a big problem for our air and water. Food that is trapped inside a landfill doesn’t get the air it needs to break down into compost. Instead, it does two things. First it releases a gas called methane. This gas is very flammable and can cause dangerous fires on the landfill site. Methane also warms the Earth’s atmosphere and causes climate change.Second, the food releases water that trickles down to the bottom of the landfill pile. The bottom layers of trash soak in this water and, because some of it – like plastic – contains poisonous chemicals, over time a toxic black liquid is formed. This liquid has a special name, “leachate”, and it can leak out of the landfill and into our rivers, lakes and groundwater.\n" +
        "Composting means we can avoid all of this! It’s an easy way to help slow climate change and keep our drinking water and aquatic environments safe and clean.\n" +
        "Organic material makes up somewhere between a quarter to a half of our total household waste, so composting will also massively reduce the amount of trash in our bins and the space taken up by landfills.");

tv4.setText("1. Select your food scraps.\n" +
        "Start with fruits and veggies — the skin of a sweet potato, the top of your strawberry. Also tea bags, coffee grounds, eggshells, old flowers — even human hair!\n" +
        "\n" +
        "Meat and dairy products, though, are asking for trouble. Leonard Diggs is the director of operations at the Pie Ranch Farm in Pescadero, California. He says you gotta ask yourself, \"Do you attract rodents? Do you attract animals to your pile? Meat products are likely to do that.\"\n" +
        "\n" +
        "Other things that may attract pests? Cooked food, oily things, buttery things and bones.\n" +
        "\n" +
        "Also important to note that some products say \"compostable\" on them — like \"compostable bags\" and \"compostable wipes.\" Those are compostable in industrial facilities, but they don't really work for home composting.\n" +
        "\n" +
        "2. Store those food scraps.\n" +
        "When you're composting, your kitchen scraps should be part of a deliberate layering process to speed up decomposition. There's a method for adding them to the pile (see step 4!), so you'll need to store them in a container so you can add them bit by bit.\n" +
        "\n" +
        "\"It doesn't have to be, you know, all the things that you find online that are really cute little ceramic containers,\" says Diggs. He says it \"can just be an old milk carton. When you make the first chop of the butt of that asparagus, boom, it could go right in there.\"\n" +
        "\n" +
        "Also, you can store the food scraps in a bag in your freezer or the back of the fridge. That's an easy way to avoid odors and insects in your kitchen.\n" +
        "\n" +
        "3. Choose a place to make your compost.\n" +
        "Worms Make Great Pets, And Other Reasons To Compost At Home\n" +
        "For this step, you gotta think about the space you're currently living in. (I'm sure none of us have thought about this recently ... Kidding!)\n" +
        "\n" +
        "If you don't have a backyard and still want a traditional composting experience you can take your food scraps to a compost pile that you share with neighbors or at a community garden.\n" +
        "\n" +
        "(Of course, in the age of the coronavirus, make sure your community garden is open, and practice social distancing.)\n" +
        "\n" +
        "If you want to break down your food scraps in your own apartment, there are still options. Jeffrey Neal, the head of the Loop Closing composting business in Washington, D.C., is a big fan of worms. He says you don't need a big container for \"vermicomposting\" — a 5 gallon box will do. Or you can go bigger.\n" +
        "\n" +
        "\"There are times when I made [my worm box] an ottoman so I could relax with my feet up on them! You can use it like a piece of furniture.\"\n" +
        "\n" +
        "Another small space idea, Neal says, is fermenting your food scraps with a Japanese method called Bokashi. \"All you need is a container you can seal and Bokashi mix, a colony of bacteria on grain.\" (Here's some more info on how to use worms and Bokashi.)\n" +
        "\n" +
        "Of course, it's totally fine if you want to give your food scraps to someone else to make compost. Some municipalities will pick up your food scraps from your home. You can also ask your local grocery stores, restaurants or farmers markets to see if they have programs to take food scraps.\n" +
        "\n" +
        "If you do have some outdoor space, your compost bin doesn't have to be complicated. \"I think keeping it simple,\" Diggs says. An old trash bin, an old wooden chest — just work with what you have available.\n" +
        "\n" +
        "You can also buy a bin online or Digg says, \"You could just create the pile naked!\" Basically you can just have a heap of compost — but don't put it up against a wall as it could stain it.\n" +
        "\n" +
        "4. Make the compost mix.\n" +
        "In the world of composting you're inevitably gonna hear about \"the greens and browns\" — the two main ingredients for your mix.\n" +
        "\n" +
        "\"Greens\" are typically food scraps, like fruit and vegetable peelings, coffee grounds, or, if you have a yard, grass clippings. These add nitrogen — a crucial element for microbial growth. Microorganisms are the true heroes of this process, they do the heavy lifting of decomposition.\n" +
        "\n" +
        "\"Browns\" are more carbon rich — think egg cartons, newspapers, dried leaves, and pine needles. It helps to shred up the paper products before putting them in your pile.\n" +
        "\n" +
        "A good thing to remember is that green materials are typically wet, and brown materials are typically dry. When you're layering, you want the dry browns on the bottom with the wet greens on the top.\n" +
        "\n" +
        "Diggs says the browns are key because they allow water to flow, and air to flow, something called aeration. That will make sure microorganisms can do their job. \"If one hundred percent of it is water, then nothing is going on. The microorganisms can't work. You got this soggy, smelly pile,\" Diggs says, \"So drainage makes a difference.\"\n" +
        "\n" +
        "A helpful analogy is to think of tending to your compost like tending a fire. Just as in a fire you need to structure the wood to get the air going, in compost you have to do a similar thing, adding spaces to give oxygen to those heroic microbes.\n" +
        "\n" +
        "And it really is layering — browns then greens, browns then greens. The number of layers depends on your space and your amount of food scraps, but try to keep the layers to an inch or two. You can also put a little bit of browns on the very top to keep away flies and odors.\n" +
        "\n" +
        "As for the ratio of \"browns\" to \"greens,\" you often hear three or four parts of browns to one part greens. Sometimes two to one. Ultimately you always want more browns than greens — again, gotta have the dry to sop up the wet.\n" +
        "\n" +
        "5. Wait and Aerate\n" +
        "How long do you have to wait for decomposition? \"If it's hot, you could get there in two months pretty easy, \" Diggs says, \"If it's cold made, you could be there in six months. And for every component to break down, it might be a year.\"\n" +
        "\n" +
        "To keep things moving, you'll want to turn or rotate the pile, perhaps with a stick or spade. Remember the fire analogy — you gotta make sure the air is flowing, that it's wet but not too soggy.\n" +
        "\n" +
        "As for how much you turn it, you'll probably turn it less if you have the right ratio of greens to browns. Diggs says when you start out you might be turning the compost once every seven to 10 days.\n" +
        "\n" +
        "Typically the more compost you have, the faster it will go.\n" +
        "\n" +
        "Neal says in the end \"the nose knows\" when your compost is ready. \"Bad compost smells, well, bad,\" he says, \"It's like what a smelly trash can or dumpster smells like ... Basically, it smells like a landfill.\"\n" +
        "\n" +
        "If it smells bad, it probably means it's not decomposing — maybe your pile might be too wet or you might need to readjust your ratios of greens and browns.\n" +
        "\n" +
        "Diggs says he loves smelling finished compost,\"You know, it just smells so ... Oh, gosh. Woody, earthy, but also a sweet smell. Or sometimes a sour smell. And the feel! How fluffy it is!\"\n" +
        "\n" +
        "When you've got that fluffy, earthy compost, put it in your garden, or in a plant on your windowsill. Or you can donate to your local community garden — just be sure to text ahead!\n" +
        "\n" +
        "Of course composting takes patience — you might run into unexpected things. We don't want you to give up so here are some more resources below.");    }
}