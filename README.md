# Compliance Hub

Compliance Hub is the new way to do compliance. As we speak, engineers around the
world who build the tools we use to run daily life - the cars that get around, the
medicines that protect from illness - are being regulated into oblivion.

Engineers today arguably have to deal with more regulations than ever before when
designing a new product. These products keep us safe, but what do we when the tool
we use to fill out them out - paper - slows innovation down to a complete stop?

## Motivation

Compliance Hub is a tool borne of the internet. What if the most innovative car
design, or the medicine most effective at saving lives, is the one that never
came out? And it was all because the engineers behind those projects could never pass
government approval, due to being buried in mountains of paperwork?

Innovation is pricier than we realize. In this chart from the MIT Technology Review's
March/April 2019,
(https://www.technologyreview.com/s/612898/ai-is-reinventing-the-way-we-invent/),
a clear relationship emerges between the amount of innovation that R&D has produced
over the pass several decades. How is this happening? One reason is rising regulations.

While they keep us safe, the fact of the matter is most government regulators still
require engineers to submit their compliance forms on paper. Additionally, there
aren't many tools to help engineers keep track of their forms.

This system makes for an inefficient, wasteful, and disjointed process for
innovative products to get approval, even when they are safe.

Compliance Hub is a replacement for the system. It's a web tool which car
manufacturers (and other types of engineers in the future, which I earnestly
hope contributions to this project will help me with) can use to track the number of
, and the types forms they need to pass each regulation for their new product.

The internet. A faster, more cost-effective, and responsive way to track government
compliance!

## Code Structure
This project was made using Python, HTML, and CSS. It uses Flask as a framework for
the backend web server, and Flask-PyMongo is used to drive a NoSql database for the
regulations needed for the car, and the documents the user submits which are related to
each requirement.

The two Collections in the database are requirements and documents. One requirement has
many documents, and one document belongs to one requirement. In the database, the
association is modeled using value association: each requirement has a "name" field,
the value of which maps to a corresponding "requirement" field in each of its
related documents.

The controllers for the app are in the app.py file. The templates are made using Jinja2,
and are located in the templates folder. The styling is done using CSS, which is found in
the css folder, which in turn is found in the static folder as per Flask conventions.

## Design
User starts by viewing a Requirement, listed on the root directory of the site.
Later on, user can add more documents (also called "forms", or "submissions" in the code)
right from the root directory, or from the page displaying each individual requirement.
User can also replace forms they have submitted with other files (all that is really saved
database is the file name, and the related requirement), or delete files they have
previously uploaded.

When the user has the required number of forms submitted, the website will inform them
they have fulfilled the corresponding requirement.

## Future Improvements
Currently, the system for passing a requirement is simply by number. I hope to make
it more comprehensive in the future, such as by using computer vision to actually scan the
data in the files, or even include a web form so the user never has to use paper throughout
the process. Of course, user authentication should also be added.

Please share this open source project with any engineers you know that will benefit from it,
to streamline the workflow they go through in tracking their compliance forms.
It would take a long time to get the government to switch away from paper, and that's why
collaboration is so important to this project. We can start reducing our paper waste, and
increasing the amount of innovation we produce - it's up to you to help make this happen!
