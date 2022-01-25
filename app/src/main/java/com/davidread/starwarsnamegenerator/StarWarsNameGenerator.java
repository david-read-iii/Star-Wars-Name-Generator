package com.davidread.starwarsnamegenerator;

import androidx.annotation.NonNull;

/**
 * {@link StarWarsNameGenerator} generates Star Wars sounding full names given some attributes about
 * the user in its constructor.
 */
public class StarWarsNameGenerator {

    /**
     * {@link String} for first name. Should be two or more characters long.
     */
    private final String firstName;

    /**
     * {@link String} for last name. Should be three or more characters long.
     */
    private final String lastName;

    /**
     * {@link String} for city born. Should be three or more characters long.
     */
    private final String cityBorn;

    /**
     * {@link String} for mother's maiden name. Should be two or more characters long.
     */
    private final String motherMaidenName;

    /**
     * Constructs a new {@link StarWarsNameGenerator}.
     *
     * @param firstName        {@link String} for first name. Should be two or more characters long.
     * @param lastName         {@link String} for last name. Should be three or more characters
     *                         long.
     * @param cityBorn         {@link String} for city born. Should be three or more characters
     *                         long.
     * @param motherMaidenName {@link String} for mother's maiden name. Should be two or more
     *                         characters long.
     */
    public StarWarsNameGenerator(@NonNull String firstName, @NonNull String lastName,
                                 @NonNull String cityBorn, @NonNull String motherMaidenName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cityBorn = cityBorn;
        this.motherMaidenName = motherMaidenName;
    }

    /**
     * Returns a {@link String} Star Wars name given the string arguments passed into the
     * constructor of this {@link StarWarsNameGenerator}.
     *
     * @return A {@link String} Star Wars name.
     * @throws IllegalStateException Thrown when one of the {@link String} objects passed into the
     *                               constructor is too short to get a Star Wars name.
     */
    public String getStarWarsName() throws IllegalStateException {

        // Throw an exception if at least one string is too short to get a Star Wars name.
        if (firstName.length() < 2
                || lastName.length() < 3
                || cityBorn.length() < 3
                || motherMaidenName.length() < 2) {
            throw new IllegalStateException(
                    "At least one string argument passed to the constructor of this "
                            + this.getClass().getSimpleName()
                            + " is too short to get a Star Wars name");
        }

        /* Build Star Wars first name from first three letters of last name and first two letters of
         * first name. */
        StringBuilder starWarsFirstName = new StringBuilder();
        starWarsFirstName.append(lastName.substring(0, 3).toLowerCase());
        starWarsFirstName.append(firstName.substring(0, 2).toLowerCase());
        starWarsFirstName.replace(0, 1, starWarsFirstName.substring(0, 1).toUpperCase());

        /* Build Star Wars last name from first two letters of mother's maiden name and first three
         * letters of city born. */
        StringBuilder starWarsLastName = new StringBuilder();
        starWarsLastName.append(motherMaidenName.substring(0, 2).toLowerCase());
        starWarsLastName.append(cityBorn.substring(0, 3).toLowerCase());
        starWarsLastName.replace(0, 1, starWarsLastName.substring(0, 1).toUpperCase());

        return starWarsFirstName.toString() + " " + starWarsLastName.toString();
    }
}
