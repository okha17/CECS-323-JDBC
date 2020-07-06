CREATE TABLE WritingGroups (
GroupName varchar(40) NOT NULL, --primary key of the table
HeadWriter varchar(40) NOT NULL,
YearFormed INTEGER NOT NULL,
Subject varchar(40) NOT NULL,

constraint WritingGroups_pk PRIMARY KEY (GroupName) -- defines the primary key
);

CREATE TABLE Publishers (
PublisherName varchar(40) NOT NULL, -- primary key
PublisherAddress varchar(40) NOT NULL,
PublisherPhone varchar(40) NOT NULL,
PublisherEmail varchar(40) NOT NULL,

constraint Publishers_pk PRIMARY KEY (PublisherName) -- single attribute since all publisher names are unique
);

CREATE TABLE Books(
GroupName varchar(40) NOT NULL, -- primary key attribute, foreign key for WritingGroups
BookTitle varchar(50) NOT NULL, -- primary key attribute, part of CK
PublisherName varchar(40) NOT NULL, -- foreign key for Publishers, CK attribute
YearPublished INTEGER NOT NULL,
NumberPages INTEGER NOT NULL,

constraint Books_ck UNIQUE (BookTitle, PublisherName), -- candidate key, refrences Publishers
constraint Books_pk PRIMARY KEY (GroupName, BookTitle), -- defines Books PK
constraint Books_fk FOREIGN KEY (PublisherName) references Publishers(PublisherName), -- defines foreign key, references Publishers
constraint Books_fk2 FOREIGN KEY (GroupName) references WritingGroups (GroupName) -- defines foreign key, references WritingGroups
);

--Following Insertion statements adds data to WritingGroups
INSERT INTO WritingGroups
    values ('CoronaChan Writing Group', 'Corona Chan', 2020, 'Health');
INSERT INTO WritingGroups
    values ('Big Homie Writing Coalition', 'Big Homie', 1969, 'Entertainment');
INSERT INTO WritingGroups
    values ('A Pimp Named Slickback Writing', 'Pimp Slickback', 2000, 'Entertainment');
INSERT INTO WritingGroups
    values ('FGC Writing Corporation', 'Joseph Marquez', 1996, 'Education');
INSERT INTO WritingGroups
    values ('CSULB Writing', 'Jane Conoley', 1822, 'Education');
INSERT INTO WritingGroups
    values ('Flat Earth Writing', 'Rocket Man', 2019, 'Science');
Insert into WritingGroups
    values ('Cooking Gods', 'Gordon Ramsay', 2013, 'Food and Cooking');

-- following inserts data into Publishers table
INSERT INTO Publishers
    values('Daigo Umehara', '720 S Harris Avenue', '4243861826', 'daigo.kenmaster@gmail.com');
INSERT INTO Publishers
    values('Alex Jones', '311 Inside Job Street', '5252445423', 'alexjones@gmail.com');
INSERT INTO Publishers
    values('James Eddins', '420 Homeson Street', '7143693208', 'j.eddieman@gmail.com');
INSERT INTO Publishers
    values('Phuong Nguyen', '69420 S Marijuana Road', '6921746969', 'pnguyen@csulb.edu');
INSERT INTO Publishers
    values('Shakey Johnson', '123 Shakey Avenue', '5629700239', 'shakeyjohnson@shakeyjohnson.net');
INSERT INTO Publishers
    values('Rudolph von Heimindinger', '696 N Deutsch Road', '7143394628', 'blitzkrank@aol.com');
INSERT INTO Publishers
    values('Slick Rick', '720 S Slick Street', '3106379667', 'slickyricky@yahoo.com');
INSERT INTO Publishers
    values('Corona-chan', '492 Corona Avenue', '3137293815', 'coronaDisease@cdc.edu');
INSERT INTO Publishers
    values('Sonic Fox', '6924 S Oki Avenue', '3106924703', 'furryFGG@gmail.com');
INSERT INTO Publishers
    values('Tyler Okonma', '6924 S Oki Avenue', '3106924703', 'furryFGG@gmail.com');

-- following inserts data into Books table
INSERT INTO Books
    values('CoronaChan Writing Group', 'How to Defeat Corona-chan', 'Corona-chan', 2020, 622);
INSERT INTO Books
    values('FGC Writing Corporation', 'The Daigo Parry and Why it Was Cool', 'Daigo Umehara', 2015, 350);
INSERT INTO Books
    values('FGC Writing Corporation', 'How to Live Rent Free in the Head of an Opponent!', 'Daigo Umehara', 2014, 196);
INSERT INTO Books
    values('FGC Writing Corporation', 'GT Goku and How to Kill that Little Rat','Sonic Fox', 2018, 132);
INSERT INTO Books
    values('A Pimp Named Slickback Writing', 'How to Get Money Fast','Slick Rick', 1995, 420);
INSERT INTO Books
    values('Flat Earth Writing', 'Gravity: Does it Really Exist?','Alex Jones', 2000, 1337);
INSERT INTO Books
    values('Flat Earth Writing', 'Nothing We Believe In is Actually Right','James Eddins', 2010, 368);
INSERT INTO Books
    values('Big Homie Writing Coalition', 'How to Be a Big Homie','Phuong Nguyen', 2011, 210);
INSERT INTO Books
    values('Big Homie Writing Coalition', 'Detecting a Big Homie: How to Be Safe','Phuong Nguyen', 2004, 100);
INSERT INTO Books
    values('CSULB Writing', 'CECS: Disfunctional Chaos','Rudolph von Heimindinger', 2006, 200);
INSERT INTO Books
    values('Cooking Gods', 'How to Synthesize Lasagna','Corona-chan', 2020, 622);
INSERT INTO Books
    values('Cooking Gods', 'Cooking Water: Easier than You Think!','Shakey Johnson', 1962, 239);
INSERT INTO Books
    values('A Pimp Named Slickback Writing', 'The Rap Game','Tyler Okonma', 2011, 402);
INSERT INTO Books
    values('CSULB Writing', 'How to Overcharge for Parking','James Eddins', 1999, 73);
