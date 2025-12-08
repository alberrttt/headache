import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.*;

// ... inside a method ...
/**
 * Write a description of class Pill here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */

public class Pill extends Actor {
        static final String[] imgs = { "whitetablet", "yellow tablet" };
        String medicine;

        public Pill() {
                int imgIndex = (int) (Math.random() * imgs.length);
                String imgName = imgs[imgIndex];
                String path = "./images/pills/" + imgName + ".png";
                GreenfootImage img = new GreenfootImage(path);
                img.scale(50, 50);
                setImage(img);
        }

        public static void spawnPill(World world) {

                Actor pill = new Pill();

                int x, y;
                boolean validLocation = false;
                while (!validLocation) {
                        x = Greenfoot.getRandomNumber(world.getWidth());
                        y = Greenfoot.getRandomNumber(world.getHeight());
                        // Check if outside the 250x250 center area
                        int centerX = world.getWidth() / 2;
                        int centerY = world.getHeight() / 2;
                        int threshold = 125; // 250/2
                        if (Math.abs(x - centerX) > threshold || Math.abs(y - centerY) > threshold) {
                                validLocation = true;
                                world.addObject(pill, x, y);
                        }
                }

        }

        /**
         * Act - do whatever the Pill wants to do. This method is called whenever
         * the 'Act' or 'Run' button gets pressed in the environment.
         */
        public void collect() {
                World world = getWorld();
                world.removeObject(this);
                // correct();
                Screen.currentScreen.walkingSound.stop();
                doOrganicChemQuiz();

        }

        public void correct() {
                Greenfoot.playSound("./sounds/collect_item.mp3");
                Player.instance.incCharges(5);

        }

        public void incorrect() {
                Player.instance.charges += 2;
        }

        static final Quiz[] quizes = { new Quiz("./images/organic/acetaminophen.png",
                        "What is the correct IUPAC name for this compound?",
                        new String[] { "N-(4-hydroxyphenyl)acetamide",
                                        "2-Acetoxybenzoic acid", "N-(4-hydroxyphenyl)ethanamide", "4-Aminophenol",
                                        "4-Hydroxyacetanilide" },
                        0),

                        new Quiz("./images/organic/salicyclic.png", "What functional groups are present?",
                                        new String[] {
                                                        "Ester and alcohol",

                                                        "Carboxylic acid and phenol",
                                                        "Aldehyde and phenol",
                                                        "Ketone and carboxylic acid",
                                                        "Alcohol and ether"
                                        }, 1),

                        new Quiz("./images/organic/cysteine.png", "What amino acid is this?",
                                        new String[] {
                                                        "Methionine",
                                                        "Serine",
                                                        "Threonine",
                                                        "Tyrosine",
                                                        "Cysteine",

                                        }, 4),

                        new Quiz("./images/question.png", "Which of the following compounds is aromatic?",
                                        new String[] {
                                                        "Cyclohexane",
                                                        "Cyclobutadiene",
                                                        "Benzene",
                                                        "1,3-Butadiene",
                                                        "Cyclooctatetraene",

                                        }, 2),
                        new Quiz("./images/question.png",
                                        "What is the hybridization of the carbon atoms in ethene (C2H4)?",
                                        new String[] {
                                                        "sp3",
                                                        "sp2",
                                                        "sp",
                                                        "s",
                                                        "d",

                                        }, 1),
                        new Quiz("./images/glycol.png", "What is the common name for this compound?",
                                        new String[] {
                                                        "Ethanol",
                                                        "Ethylene glycol",
                                                        "Acetone",
                                                        "Glycerol",
                                                        "Methanol",

                                        }, 1),
        };

        public void doOrganicChemQuiz() {
                int quizIndex = (int) (Math.random() * quizes.length);
                Quiz quiz = quizes[quizIndex];

                ImageIcon icon = new ImageIcon(quiz.image);

                // Build a shuffled copy of the options so the choices appear in random order
                String[] origOptions = quiz.options;
                int n = origOptions.length;
                ArrayList<Integer> indices = new ArrayList<Integer>(n);
                for (int i = 0; i < n; i++)
                        indices.add(i);
                Collections.shuffle(indices);
                String[] shuffled = new String[n];
                int mappedAnswerIndex = -1;
                for (int i = 0; i < n; i++) {
                        int origIdx = indices.get(i);
                        shuffled[i] = origOptions[origIdx];
                        if (origIdx == quiz.answerIndex)
                                mappedAnswerIndex = i;
                }

                int opt = JOptionPane.showOptionDialog(null, quiz.question,
                                "Organic Chemistry Quiz",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                icon,
                                shuffled,
                                null);
                // String userInput = JOptionPane.showInputDialog(
                // null,
                // quiz.question + "\n\n" + optionsBuilder.toString(),
                // "Organic Chemistry Quiz",
                // JOptionPane.QUESTION_MESSAGE);

                // int userAnswer = Integer.parseInt(userInput) - 1;
                if (opt == mappedAnswerIndex) {
                        correct();

                } else {
                        JOptionPane.showMessageDialog(null,
                                        "Incorrect! The correct answer was: " + quiz.options[quiz.answerIndex],
                                        "Result",
                                        JOptionPane.ERROR_MESSAGE);
                        incorrect();
                }

        }
}

class Quiz {
        public String image;
        public String question;
        public String[] options;
        public int answerIndex;

        public Quiz(String image, String question, String[] options, int answerIndex) {
                this.image = image;
                this.question = question;
                this.options = options;
                this.answerIndex = answerIndex;
        }
}