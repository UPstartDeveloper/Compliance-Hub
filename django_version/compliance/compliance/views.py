from django.shortcuts import render


def landing(request):
    '''Render the landing page of Compliance Hub.'''
    return render(request, 'index.html')
